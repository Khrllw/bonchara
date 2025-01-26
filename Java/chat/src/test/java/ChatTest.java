import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.serverchat.ClientHandler;
import org.serverchat.Server;

import javax.net.ssl.*;
import java.io.*;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ChatTest {
    private Server server;

    @Mock
    private SSLServerSocket mockServerSocket;

    @Mock
    private SSLSocket mockSocket;

    @Mock
    private SSLContext mockContext;

    @BeforeEach
    void setup() throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("User".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(mockSocket.getInputStream()).thenReturn(inputStream);
        when(mockSocket.getOutputStream()).thenReturn(outputStream);
        when(mockServerSocket.accept()).thenReturn(mockSocket);

        server = spy(new Server());
        doReturn(mockContext).when(server).setupSSLContext();
        doReturn(mockServerSocket).when(server).createServerSocket(mockContext);
        doNothing().when(server).acceptClientConnection();

        new Thread(() -> {
            try {
                server.startServer();
            } catch (Exception ignored) {
            }
        }).start();

        while (!Server.isServerRunning()) {
            Thread.sleep(50);
        }
    }

    @AfterEach
    void cleanup() {
        server.stopServer();
    }

    @Test
    void testVerifyAndRegisterUsernameSuccess() throws IOException, InterruptedException {
        simulateClient("User");
        List<ClientHandler> connectedClients = ClientHandler.getConnectedClients();

        Assertions.assertEquals(1, connectedClients.size(), "One client should be connected.");
        Assertions.assertEquals("User", connectedClients.get(0).username, "Username should be registered successfully.");
    }

    @Test
    void testVerifyAndRegisterUsernameAlreadyTaken() throws IOException, InterruptedException {
        simulateClient("User");
        ClientHandler secondClient = simulateClient("User");

        Assertions.assertNull(secondClient.username, "Username should not be registered if already taken.");
        Assertions.assertEquals(1, ClientHandler.getConnectedClients().size(), "Only one client should be connected.");
    }

    @Test
    void testVerifyAndRegisterUsernameSuperAdmin() throws IOException, InterruptedException {
        ClientHandler adminClient = simulateClient("Админ1270018040");

        Thread.sleep(200);

        Assertions.assertTrue(ClientHandler.superAdminConnected, "SuperAdmin flag should be set.");
        Assertions.assertEquals("Админ1270018040", adminClient.username, "SuperAdmin should be registered successfully.");
    }

    @Test
    void testServerStopsSuccessfully() {
        Assertions.assertTrue(Server.isServerRunning(), "Server should be running initially.");

        server.stopServer();

        Assertions.assertFalse(Server.isServerRunning(), "Server should be stopped.");
    }

    private ClientHandler simulateClient(String username) throws IOException, InterruptedException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream((username + "\n").getBytes());
        when(mockSocket.getInputStream()).thenReturn(inputStream);

        SSLSocket clientSocket = (SSLSocket) mockServerSocket.accept();
        ClientHandler clientHandler = new ClientHandler(clientSocket);

        Thread clientThread = new Thread(clientHandler);
        clientThread.start();
        clientThread.sleep(3000);
        return clientHandler;
    }
}
