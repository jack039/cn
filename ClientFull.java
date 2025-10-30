import java.io.*;
import java.net.*;

public class ClientFull {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java ClientFull <server-ip> <port>");
            return;
        }
        String serverIp = args[0];
        int port = Integer.parseInt(args[1]);

        Socket socket = new Socket(serverIp, port);
        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        Thread reader = new Thread(() -> {
            try {
                String line;
                while ((line = socketIn.readLine()) != null) {
                    System.out.println("Server: " + line);
                }
            } catch (IOException e) {
                // socket closed
            }
        });
        reader.start();

        String input;
        while ((input = console.readLine()) != null) {
            socketOut.println(input);
            if (input.equalsIgnoreCase("exit")) break;
        }

        socket.close();
        System.out.println("ClientFull stopped.");
    }
}  //java ClientFull 127.0.0.1 5000