## Socket
- A socket is one endpoint of a two-way communication link between two programs running on the network. A socket is bound to a port number so that the TCP layer can identify the application that data is destined to be sent to.

**To we information between computer, we need:**

**ip address** : unique id on the (local) network

**port** : unique id number on your computer linked to your program

**packet**: unit of data sent from one computer to another. Data is sent separately in the Internet
and reconstruct in the receiving end. Each packet has an ip address and the port number.

## Multiple Connections
Create Server -> Create Client -> Client points to the server and Server accepts the connection

Client type something -> BufferedReader(InputStreamReader) -> PrintWriter(socket.getOutputStrem()) 
-> ClientHandler BufferedReader reads the PrintWriter from Client -> Process and pass to ClientHandler PrintWriter
-> ServerConnection BufferedReader received the data from ClientHandler PrinterWriter -> print the result to the client

If we run a server and two clients, we can see the socket in netstat
```
netstat -an | grep 9090
tcp4       0      0  127.0.0.1.9090         127.0.0.1.55357        ESTABLISHED
tcp4       0      0  127.0.0.1.55357        127.0.0.1.9090         ESTABLISHED
tcp4       0      0  127.0.0.1.9090         127.0.0.1.55352        ESTABLISHED
tcp4       0      0  127.0.0.1.55352        127.0.0.1.9090         ESTABLISHED
tcp46      0      0  *.9090                 *.*                    LISTEN
```

[Reference](https://www.youtube.com/watch?v=BWjGQlIkgT4)