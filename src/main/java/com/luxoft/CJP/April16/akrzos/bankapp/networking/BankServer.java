package com.luxoft.CJP.April16.akrzos.bankapp.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by arkad_000 on 2016-04-28.
 */
public class BankServer {
    ServerSocket serverSocket;
    Socket connection = null;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    String message;

    void run() {
        try {
            // 1. creating a server socket
            serverSocket = new ServerSocket(2004, 10);
            // 2. Wait for connection
            System.out.println("Waiting for connection");
            connection = serverSocket.accept();
            System.out.println("Connection received from "
                    + connection.getInetAddress().getHostName());
            // 3. get Input and Output streams
            oos = new ObjectOutputStream(connection.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(connection.getInputStream());
            sendMessage("Connection successful");
            // 4. The two parts communicate via the input and output streams
            do {
                try {
                    message = (String) ois.readObject();
                    System.out.println("client>" + message);
                    if (message.equals("bye"))
                        sendMessage("bye");
                } catch (ClassNotFoundException classnot) {
                    System.err.println("Data received ois unknown format");
                }
            } while (!message.equals("bye"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            // 4: Closing connection
            try {
                ois.close();
                oos.close();
                serverSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    void sendMessage(final String msg) {
        try {
            oos.writeObject(msg);
            oos.flush();
            System.out.println("server>" + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(final String args[]) {
        BankServer server = new BankServer();
        while (true) {
            server.run();
        }
    }
}