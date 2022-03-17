package MultipleConnections;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static String[] names = { "Wily", "Peter", "Tom", "Sam" };
    private static String[] adjs = { "the gentle", "the brave", "the overwrought", "the urbane" };
    private static final int PORT = 9090;

    // Store all the threads
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4); // can connect up to 4 clients at a time

    // When client connects, server will send the current date
    public static void main(String[] args) throws IOException {
        try (
                // listen for client connections
                ServerSocket listener = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("[SERVER] Waiting for client connection...");
                Socket clientSocket = listener.accept();
                System.out.println("[SERVER] Connected to client");

                ClientHandler clientThread = new ClientHandler(clientSocket, clients);
                clients.add(clientThread);

                pool.execute(clientThread);
            }
        }
    }

    public static String getRandomName() {
        String name = names[(int) (Math.random() * names.length)];
        String adj = adjs[(int) (Math.random() * names.length)];
        return name + " " + adj;
    }
}
