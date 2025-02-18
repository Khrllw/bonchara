import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private final InetAddress host;
    private final int port;
    //String clientName;

    public Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    //Создает сокет и запускает сновную логику приложения
    public void start() {
        //Создаем клиентский сокет
        try (Socket socket = new Socket(this.host, this.port)) {

            //Запуск логики приложения
            this.logicStart(socket);

            //socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logicStart(Socket socket) throws IOException {
        //Логика приложения
        try (socket;
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream());) {

            System.out.println("Client connected to socket.");
            System.out.println();
            System.out.println("Client writing channel = oos & reading channel = ois initialized.");

            // проверяем живой ли канал и работаем если живой
            while (!socket.isOutputShutdown()) {

                // ждём консоли клиента на предмет появления в ней данных
                if (br.ready()) {

                    // данные появились - работаем
                    System.out.println("Client start writing in channel...");

                    String clientCommand = br.readLine();

                    // пишем данные с консоли в канал сокета для сервера
                    oos.writeUTF(clientCommand);
                    oos.flush();
                    System.out.println("Clien sent message " + clientCommand + " to server.");

                    // ждём чтобы сервер успел прочесть сообщение из сокета и ответить

                    // проверяем условие выхода из соединения
                    if (clientCommand.equalsIgnoreCase("quit")) {

                        // если условие выхода достигнуто разъединяемся
                        System.out.println("Client kill connections");

                        // смотрим что нам ответил сервер на последок перед закрытием ресурсов
                        String in = ois.readUTF();
                        if (in.length() > 0) {
                            System.out.println("reading...");
                            System.out.println(in);
                        }

                        // после предварительных приготовлений выходим из цикла записи чтения
                        break;
                    }

                    // если условие разъединения не достигнуто продолжаем работу
                    System.out.println("Client sent message & start waiting for data from server...");


                    // проверяем, что нам ответит сервер на сообщение(за предоставленное ему время в паузе он должен был успеть ответить)
                    String in = ois.readUTF();
                    if (in.length() > 0) {

                        // если успел забираем ответ из канала сервера в сокете и сохраняем её в ois переменную,  печатаем на свою клиентскую консоль
                        System.out.println("reading...");
                        System.out.println(in);
                    }
                }
            }
            // на выходе из цикла общения закрываем свои ресурсы
            System.out.println("Closing connections & channels on clentSide - DONE.");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}