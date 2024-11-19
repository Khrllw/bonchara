import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.StringTokenizer;

// основная логика каждой сессии клиента
public class ClientSession extends Thread {
    static final String REGEX_URL_SPLIT = "/";
    private static final String DEFAULT_FILES_DIR = "";
    private final Socket socket;

    BufferedReader requestReader;
    PrintWriter headerWriter;
    BufferedOutputStream contentWriter;

    private final Context context;

    public ClientSession(final Socket socket, final Context context) throws IOException, IOException {
        // задаем клиентский сокет
        this.socket = socket;
        System.out.println("\n\033[1;32m[ Connection accepted ]\033[0m");

        // задаем ридер и райтер для обмена сообщениями
        this.requestReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("\033[32m<Data>InputStream created\033[0m");

        this.headerWriter = new PrintWriter(socket.getOutputStream());
        this.contentWriter = new BufferedOutputStream(socket.getOutputStream());
        System.out.println("\033[32m<Data>OutputStream created\033[0m");

        this.context = context;
    }

    /* Simple HTTP response
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println();
    out.println("<html><body><h1>Hello, World!</h1></body></html>");
 */


    public void run() {
        try {
            // Обработка сообщения от клиента
            StringTokenizer parse = new StringTokenizer(requestReader.readLine());
            // получение HTTP метода, вызвавшего сервер
            String method = parse.nextToken().toUpperCase();
            System.out.println("\033[1;31m[ Received method: " + method + " ]\033[0m");

            // Получение запроса
            String requested = parse.nextToken().toLowerCase();
            System.out.println("\033[1;31m[ Received request: " + requested + " ]\033[0m");

            // Check if the incoming request is a GET request
            if (!method.equals("GET")) {
                System.out.println("501 Not implemented : " + method + " method.");
                // Send a message that the user uses the wrong method at this server
                sendJson(headerWriter, contentWriter, 501, "{\"error\":\"Method not implemented. Please use GET instead\"}");
            } else {
                // Get the path that was called
                // If "https://example.com/any/thing" gets called, returns an array of "any" and "thing"
                String[] urlSplit = requested.split(REGEX_URL_SPLIT);
                String path = makePath(urlSplit);
                System.out.printf(path);
                sendJSP(headerWriter, contentWriter, 200, readFileToString(path));

                // Here goes your code
                // [...]
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String readFileToString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (FileReader fileReader = new FileReader(new File(fileName));
             BufferedReader reader = new BufferedReader(fileReader)) {
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str.replaceAll(" ", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String makePath(String[] url) {
        StringBuilder builder = new StringBuilder();
        for (String s : url) {

            builder.append(REGEX_URL_SPLIT);
            builder.append(s);
        }
        builder.deleteCharAt(0);
        builder.deleteCharAt(0);
        return builder.toString();
    }

    static void sendJSP(PrintWriter headerWriter, BufferedOutputStream contentWriter, int statusCode, String content) throws IOException {
        // send HTML code as response
        write(headerWriter, contentWriter, statusCode, "text/jsp", content.getBytes(StandardCharsets.UTF_8), content.length());
    }

    static void sendHtml(PrintWriter headerWriter, BufferedOutputStream contentWriter, int statusCode, String content) throws IOException {
        // send HTML code as response
        write(headerWriter, contentWriter, statusCode, "text/html", content.getBytes(StandardCharsets.UTF_8), content.length());
    }

    static void sendPlain(PrintWriter headerWriter, BufferedOutputStream contentWriter, int statusCode, String content) throws IOException {
        // send plain text as response
        write(headerWriter, contentWriter, statusCode, "text/plain", content.getBytes(StandardCharsets.UTF_8), content.length());
    }

    // TODO: Check if JSON is valid. If not print error message
    static void sendJson(PrintWriter headerWriter, BufferedOutputStream contentWriter, int statusCode, String json) throws IOException {
        // send json as response
        write(headerWriter, contentWriter, statusCode, "application/json", json.getBytes(StandardCharsets.UTF_8), json.length());
    }

    static void write(PrintWriter headerWriter, BufferedOutputStream contentWriter, int statusCode, String contentType, byte[] response, int responseLength) throws IOException {
        // write a plain request as response. All have to be setup in the methods parameters

        // Fetch the StatusCode from the integer
        HttpStatusCode httpStatusCode = HttpStatusCode.getByResult(statusCode);

        // Write the headers of the response
        headerWriter.println(String.format("HTTP/1.1 %d %s", statusCode, httpStatusCode == null ? "Unknown" : httpStatusCode.name()));
        headerWriter.println("Server: HTTP Server : 1.0");
        headerWriter.println("Date: " + new Date());
        headerWriter.println("Content-type: " + contentType);
        headerWriter.println("Content-length: " + responseLength);
        headerWriter.println();
        headerWriter.flush();

        // Write the content of the response
        contentWriter.write(response, 0, responseLength);
        contentWriter.flush();
    }

}