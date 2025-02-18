import java.io.IOException;
import java.net.InetAddress;

public class ClientLauncher {
    public static void main(String[] args) {
        try {
            InetAddress host = InetAddress.getByName(args[0]);
            int port = Integer.parseInt(args[1]);

            Client client = new Client(host, port);

            //Запускаем логику клиента
            client.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}