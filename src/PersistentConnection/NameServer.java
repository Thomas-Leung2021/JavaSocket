package PersistentConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NameServer {
    private static String[] names = { "Wily", "Peter", "Tom", "Sam" };
    private static String[] adjs = { "the gentle", "the brave", "the overwrought", "the urbane" };
    private static final int PORT = 9090;

    // When client connects, server will send the current date
    public static void main(String[] args) throws IOException {
        // listen for client connections
        ServerSocket serverSocket = new ServerSocket(PORT);

        System.out.println("[SERVER] Waiting for client connection...");
        // THE CODE BELOW IS ONLY FOR A PARTICULAR CLIENT THAT CONNECTS TO THE SERVER
        // (No threading)
        Socket client = serverSocket.accept();
        System.out.println("[SERVER] Connected to client");

        PrintWriter out = new PrintWriter(client.getOutputStream(), true); // write to client
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream())); // read from client

        try {
            while (true) {
                String request = in.readLine(); // command from client
                if (request.contains("name")) {
                    out.println(getRandomName());
                } else {
                    out.println("Type 'name' to get a random name");
                }
            }
        } finally {
            client.close();
            serverSocket.close();
        }
    }

    public static String getRandomName() {
        String name = names[(int) (Math.random() * names.length)];
        String adj = adjs[(int) (Math.random() * names.length)];
        return name + " " + adj;
    }
}
