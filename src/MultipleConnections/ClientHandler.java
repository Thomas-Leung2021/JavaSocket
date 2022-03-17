package MultipleConnections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

// for server to handle each client
public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {
        // server accepts the request for the connection, then the handler will handle
        // the socket
        this.clientSocket = clientSocket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        out = new PrintWriter(this.clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String request = in.readLine(); // command from client

                if (request == null)
                    break;

                if (request.contains("name")) {
                    out.println(Server.getRandomName());
                } else if (request.contains("say")) {
                    int firstSpace = request.indexOf(" ");
                    // send message to all clients after the word "say"
                    if (firstSpace != -1) {
                        outToAll(request.substring(firstSpace + 1));
                    }
                } else {
                    out.println("Type 'name' to get a random name");
                }
            }
        } catch (IOException e) {
            System.err.println("IO exception in client handler");
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.close();
        }

    }

    private void outToAll(String message) {
        for (ClientHandler client : clients) {
            client.out.println(Thread.currentThread().getName() + " says: " + message);
        }
    }
}
