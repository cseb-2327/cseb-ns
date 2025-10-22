import javax.net.ssl.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Trust the server keystore (for testing)
            System.setProperty("javax.net.ssl.trustStore", "server.jks");   
            System.setProperty("javax.net.ssl.trustStorePassword", "123456");

            SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket) sf.createSocket("localhost", 9999);

            // Enable TLSv1.2
            socket.setEnabledProtocols(new String[]{"TLSv1.2"});

            // Send data to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello, server!");

            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
