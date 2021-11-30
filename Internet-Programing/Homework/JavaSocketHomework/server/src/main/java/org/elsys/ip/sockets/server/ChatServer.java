package org.elsys.ip.sockets.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        public CalculatorClientHandler(Socket socket, ChatServer server) {
            this.clientSocket = socket;
            this.server = server;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                while (true) {
                    String line = in.readLine();
                    String lineToLower = line.toLowerCase();
                    if ((lineToLower.equals("quit") || lineToLower.equals("exit"))) {
                        clientSocket.close();
                        dispose();
                    }
                    if (lineToLower.equals("time")){
                        String zone = in.readLine();
                        LocalTime time = LocalTime.now(ZoneId.of(zone));
                        String t = time.format(DateTimeFormatter.ofPattern("HH:mm"));
                        out.println(t);
                    }else if(!lineToLower.substring(0,4).equals("time")){
                        out.println("invalid input");
                    }else if((lineToLower.split(" ")[1].length() == 6)) {
                        String[] arguments = lineToLower.split(" ");
                        // Regex:
                        // (([+](0[0-9]|1[0-4])|[-](0[0-9]|1[0-2])):[0][0])
                        String regex = "(([+](0[0-9]|1[0-4])|[-](0[0-9]|1[0-2])):[0][0])";
                        Pattern p = Pattern.compile(regex);
                        Matcher m = p.matcher(arguments[1]);
                        if (m.matches()) {
                            //Do logic
                            String timeOffset = arguments[1];
                            String stringHour = timeOffset.substring(1,3);
                            int hour = Integer.parseInt(stringHour);
                            if (timeOffset.charAt(0) == '+') {
                                LocalTime time = LocalTime.now(ZoneOffset.ofHours(hour));
                                String t = time.format(DateTimeFormatter.ofPattern("HH:mm"));
                                out.println(t);
                            } else {
                                hour *= -1;
                                LocalTime time = LocalTime.now(ZoneOffset.ofHours(hour));
                                String t = time.format(DateTimeFormatter.ofPattern("HH:mm"));
                                out.println(t);
                            }
                        } else {
                            //quit invalid args
                            out.println("invalid time zone");
                        }
                    }
                    else{
                        out.println("invalid input");
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
