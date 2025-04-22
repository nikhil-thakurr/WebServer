import java.io.*;
import java.net.*;

public class ServerSingleThreaded {
    public void run() throws IOException {
        int PORT = 8010;
        System.out.println("Attempting to start server...");
        ServerSocket socket = new ServerSocket(PORT);
        socket.setSoTimeout(10000); // 10 second timeout

        while (true) {
            try {
                System.out.println("Server is running on port: " + PORT);
                Socket acceptedConnection = socket.accept(); // Waits for client
                System.out.println("Connection accepted from: " + acceptedConnection.getRemoteSocketAddress());

                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));

                toClient.println("Hello From The Server");
                toClient.close();
                fromClient.close();
                acceptedConnection.close();

            } catch (SocketTimeoutException stex) {
                System.out.println("No client connected yet. Waiting...");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Main method started ✅");
        ServerSingleThreaded server = new ServerSingleThreaded();
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
