package org.elsys.ip.sockets.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChatServer {
    private ServerSocket serverSocket;
    private final Map<String, CalculatorClientHandler> clients = new HashMap<>();

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            CalculatorClientHandler client = new CalculatorClientHandler(serverSocket.accept(), this);
            client.start();
        }
    }

    private static class CalculatorClientHandler extends Thread {
        private final Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private final ChatServer server;
        private String name;

        public CalculatorClientHandler(Socket socket, ChatServer server) {
            this.clientSocket = socket;
            this.server = server;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                name = in.readLine();
                while (true) {
                    String line = in.readLine();
                    String lineToLower = line.toLowerCase();
                    if (line == null || (lineToLower.equals("quit") || lineToLower.equals("exit"))) {
                        break;
                    }
                }
            } catch (Throwable t) {
                System.out.println(t.getMessage());
            } finally {
                dispose();
            }
        }

        private void dispose() {
            try {
                if (clientSocket != null) clientSocket.close();
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (Throwable t) {
                System.out.println(t.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ChatServer server = new ChatServer();
        if(args.length != 1){
            System.err.println("invalid arguments");
            System.exit(1);
        }
        int port = 0;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("invalid arguments");
            System.exit(1);
        }
        if(port < 0 || port > 49152){
            System.err.println("invalid arguments");
            System.exit(1);
        }
        try {
            server.start(port);
        } catch (IOException e) {
            System.err.println("port is already in use");
            System.exit(2);
        }
    }
}
