import java.io.*;
import java.net.*;

public class ServerHalf {
    public static void main(String[] args) throws IOException {
        int port = 5000;
        if (args.length > 0) port = Integer.parseInt(args[0]);

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("ServerHalf running on port " + port + ". Waiting for client...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected from " + socket.getInetAddress());

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Read one line from client
        String clientMsg = in.readLine();
        System.out.println("Received from client: " + clientMsg);

        // Reply
        out.println("Server reply: Received '" + clientMsg + "'");

        // Clean up
        socket.close();
        serverSocket.close();
        System.out.println("ServerHalf finished.");
    }
    //java ServerHalf 5000
}