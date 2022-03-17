package SingleInteration;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DateServer {

    private static final int PORT = 9090;

    // When client connects, server will send the current date
    public static void main(String[] args) throws IOException {
        // listen for client connections
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("[SERVER] Waiting for client connection...");
        Socket client = serverSocket.accept();
        System.out.println("[SERVER] Connected to client");

        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        out.println((new Date()).toString());
        System.out.println("[SERVER] Sent date to client");

        client.close();
        serverSocket.close();
    }
}
