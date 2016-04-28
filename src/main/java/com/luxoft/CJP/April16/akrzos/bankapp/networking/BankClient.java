package com.luxoft.CJP.April16.akrzos.bankapp.networking;

        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.net.Socket;
        import java.net.UnknownHostException;

/**
 * Created by arkad_000 on 2016-04-28.
 */
public class BankClient {
    Socket requestSocket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    String message;
    static final String SERVER = "localhost";

    void run() {
        try {
            // 1. creating a socket to connect to the server
            requestSocket = new Socket(SERVER, 2004);
            System.out.println("Connected to localhost is port 2004");
            // 2. get Input and Output streams
            oos = new ObjectOutputStream(requestSocket.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(requestSocket.getInputStream());
            // 3: Communicating with the server
            do {
                try {
                    message = (String) ois.readObject();
                    System.out.println("server>" + message);
                    sendMessage("Hi my server");
                    message = "bye";
                    sendMessage(message);
                } catch (ClassNotFoundException classNot) {
                    System.err.println("data received is unknown format");
                }
            } while (!message.equals("bye"));
        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            // 4: Closing connection
            try {
                ois.close();
                oos.close();
                requestSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    void sendMessage(final String msg) {
        try {
            oos.writeObject(msg);
            oos.flush();
            System.out.println("client>" + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(final String args[]) {
        BankClient client = new BankClient();
        client.run();
    }

}

