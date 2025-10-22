import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class Server {
    public static void main(String[] args) {
        int port = 9999;
        String keystoreFile = "serverkeystore.jks";
        String keystorePassword = "password";

        try {
            // Load server keystore
            KeyStore keyStore = KeyStore.getInstance("JKS");
            try (FileInputStream keyStoreIS = new FileInputStream(keystoreFile)) {
                keyStore.load(keyStoreIS, keystorePassword.toCharArray());
            }

            // Create KeyManagerFactory and initialize with keystore
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(keyStore, keystorePassword.toCharArray());

            // Setup SSL context with key managers
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, null);

            // Create SSLServerSocketFactory from context
            SSLServerSocketFactory serverSocketFactory = sslContext.getServerSocketFactory();
            SSLServerSocket serverSocket = (SSLServerSocket) serverSocketFactory.createServerSocket(port);

            System.out.println("Server started, waiting for client connection...");

            SSLSocket sslSocket = (SSLSocket) serverSocket.accept();

            BufferedReader input = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            String clientMessage = input.readLine();
            System.out.println("Received from client: " + clientMessage);

            input.close();
            sslSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
