package MultipleConnections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements Runnable {
    private Socket server;
    private BufferedReader in;

    public ServerConnection(Socket socket) throws IOException {
        this.server = socket;
        this.in = new BufferedReader(new InputStreamReader(this.server.getInputStream()));
    }

    @Override
    public void run() {
        // Handle input from the server
        try {
            while (true) {
                // get response from server
                String serverResponse = in.readLine();
                if (serverResponse == null)
                    break;
                System.out.println("Server " + Thread.currentThread().getName() + " says: " + serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
