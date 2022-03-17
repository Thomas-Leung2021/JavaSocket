package MultipleConnections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        ServerConnection serverConnection = new ServerConnection(socket);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // write to server, and server is running on
                                                                           // a new thread

        // we run in a new thread such that out won't get blocked by the keyboard input
        // new BufferedReader(new InputStreamReader(this.server.getInputStream()) is in
        // ServerConnection
        new Thread(serverConnection).start();

        while (true) {
            System.out.println("Running on: " + Thread.currentThread().getName());
            System.out.print("> ");
            String command = keyboard.readLine(); // read from keyboard

            if (command.equals("quit"))
                break;
            out.println(command); // send to server
        }
        socket.close();
        System.exit(0);
    }
}
