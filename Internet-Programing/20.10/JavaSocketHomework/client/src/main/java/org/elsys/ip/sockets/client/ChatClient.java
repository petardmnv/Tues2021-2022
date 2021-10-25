package org.elsys.ip.sockets.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        new Thread(() -> {
            try {
                while (true) {
                    String line = in.readLine();
                    if (line == null) {
                        System.out.println("<<<<<< Connection Closed >>>>>>");
                        System.exit(0);
                    }
                    System.out.println(line);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        }).start();
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        ChatClient client = new ChatClient();
        if(args.length != 1){
            System.err.println("invalid arguments");
            System.exit(1);
        }
        String separatedArguments[] = new String[0];
        try {
            separatedArguments = args[0].split(":");
        } catch (Exception e) {
            System.err.println("invalid arguments");
            System.exit(1);
        }
        int port = 0;
        try {
            port = Integer.parseInt(separatedArguments[1]);
        } catch (NumberFormatException e) {
            System.err.println("invalid arguments");
            System.exit(1);
        }
        if(port < 0 || port > 49152){
            System.err.println("invalid arguments");
            System.exit(1);
        }
        String host = separatedArguments[0];
        try {
            client.startConnection(host, port);
        } catch (UnknownHostException e) {
            System.err.println("invalid host");
            System.exit(3);
        }
        catch (IOException e) {
            System.err.println("connection not possible");
            System.exit(4);
        }

        while (true) {
            String line = scanner.nextLine();
            client.sendMessage(line);
        }
    }
}
