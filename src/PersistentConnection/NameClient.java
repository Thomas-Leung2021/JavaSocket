package PersistentConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class NameClient {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        while (true) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // read from
                                                                                                       // server
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); // read from keyboard
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // write to server

            System.out.print("> ");
            String command = keyboard.readLine(); // read from keyboard

            if (command.equals("quit"))
                break;
            out.println(command); // send to server

            String serverResponse = input.readLine(); // get response from server
            JOptionPane.showMessageDialog(null, serverResponse); // display response
        }
        socket.close();
        System.exit(0);
    }
}
