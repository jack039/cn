import java.io.*;
import java.net.*;

public class ClientHalf {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: java ClientHalf <server-ip> <port>");
            return;
        }
        String serverIp = args[0];
        int port = Integer.parseInt(args[1]);

        Socket socket = new Socket(serverIp, port);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        System.out.print("Type message to server: ");
        String msg = console.readLine();
        out.println(msg);                // send -> server
        String reply = in.readLine();    // receive -> server reply
        System.out.println("Server replied: " + reply);

        socket.close();
        //java ClientHalf 127.0.0.1 5000
    }
}