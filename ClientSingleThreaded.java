

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSingleThreaded {
    void run() throws UnknownHostException,IOException{
        int PORT = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address,PORT);
        PrintWriter toSocket  = new PrintWriter(socket.getOutputStream());
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toSocket.println("Hellow From The Client");
        String line = fromSocket.readLine();
        System.out.println("Response from socket is : " + line );
        toSocket.close();
        fromSocket.close();
        socket.close();
    }

    public static void main(String[] args) {
     try {
        ClientSingleThreaded client = new ClientSingleThreaded();
        client.run();
     } catch (Exception ex) {
        ex.printStackTrace();        
    }   
    }
}
