import java.io.*;
import java.net.*;

public class ServerFull {
    public static void main(String[] args) throws IOException {
        int port = 5000;
        if (args.length > 0) port = Integer.parseInt(args[0]);

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("ServerFull listening on port " + port + "...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket.getInetAddress());

        BufferedReader socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        Thread reader = new Thread(() -> {
            try {
                String line;
                while ((line = socketIn.readLine()) != null) {
                    System.out.println("Client: " + line);
                }
            } catch (IOException e) {
                // socket closed
            }
        });
        reader.start();

        // write loop
        String input;
        while ((input = console.readLine()) != null) {
            socketOut.println(input);
            if (input.equalsIgnoreCase("exit")) break;
        }

        clientSocket.close();
        serverSocket.close();
        System.out.println("ServerFull stopped.");
    }
} //java ServerFull 5000