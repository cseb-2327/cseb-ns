import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class Server {
    public static void main(String[] args) {
        try {
            // Set keystore properties
            System.setProperty("javax.net.ssl.keyStore", "server.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "123456");

            // Create SSL Server Socket
            SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket serverSocket = (SSLServerSocket) ssf.createServerSocket(9999);

            // Enable TLSv1.2
            serverSocket.setEnabledProtocols(new String[]{"TLSv1.2"});

            System.out.println("Server is listening on port 9999...");

            SSLSocket socket = (SSLSocket) serverSocket.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientMessage = input.readLine();
            System.out.println("Received from client: " + clientMessage);

            // Close connections
            input.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
