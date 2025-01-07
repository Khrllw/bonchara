import com.sun.net.httpserver.HttpServer;

import javax.naming.Context;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private final int port;
    private final Context context;

    public Server() {
        this.port = 8080;
        this.context = new Context();
    }

    @Override
    public void run() {
        try (ExecutorService executor = Executors.newCachedThreadPool();
             ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("\033[1;34m[ Server started on port: " + serverSocket.getLocalPort() + " ]\033[0m");

            // цикл ожидания подключений
            while (!this.context.stopFlag) {
                System.out.println("\nWaiting connection on port: " + this.port + "...");

                // ожидание подключения
                Socket clientSocket = serverSocket.accept();
                System.out.println("[ New client connected to server ]");
                System.out.printf("\033[34mInput: %s\033[0m\n", clientSocket.getInputStream());
                System.out.printf("\033[34mOutput: %s\033[0m\n", clientSocket.getOutputStream());

                // создание клиентской сессии
                ClientSession clientSession = new ClientSession(clientSocket, this.context);
                // запуск логики работы с клиентом
                executor.execute(clientSession);


                //PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                //out.println("Hello!");
                //this.context.stopFlag = true;
                //
                //clientSession.start();
                // if (i == 3) {
                //   executor.shutdown();
                //  context.stopFlag = true;
                //    }
            }
            // дожидаемся завершения всех активных сессий пользователей

            // while (!context.getSessionsManger().getSessions().isEmpty()) {
            //   TimeUnit.SECONDS.sleep(2);
            // Ожидаем выполнение всех задач.
            //}
            System.out.print("\033[1;32mGood BYE!\033[0m\n");
            //executor.awaitTermination();
            //serverSocket.close();

        } catch (IOException /*| InterruptedException*/ e) {
            throw new RuntimeException(e);
        }
    }
}