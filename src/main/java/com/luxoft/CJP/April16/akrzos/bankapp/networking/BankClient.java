package com.luxoft.CJP.April16.akrzos.bankapp.networking;

        import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;

        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.net.Socket;
        import java.net.UnknownHostException;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

/**
 * Created by arkad_000 on 2016-04-28.
 */
public class BankClient {
    Socket requestSocket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    String message;
    static final String SERVER = "localhost";

    private List<String> commandList;
    private Client activeClient;
    Scanner scanner = new Scanner(System.in);


    public BankClient() {
        commandList = new ArrayList<String>();
        commandList.add("Set active client");
        commandList.add("Set active account");
        commandList.add("Withdraw");

        activeClient=null;
    }

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
            BankClientMenu menu = new BankClientMenu();



            do {
                while(true) {

                    System.out.print("Active client: ");
                    if (activeClient==null) {
                        System.out.println("not set");
                    } else {
                        System.out.println(activeClient.getName());
                    }

                    for (int i = 0; i < commandList.size(); i++) {
                        System.out.println(i+ ") " + commandList.get(i));
                    }

                    //TODO 1, asking for client list
                    System.out.println("Provide client name:");
                    String pattern = scanner.nextLine(); //TODO
//                    sendMessage("GETCLIENTSLIST|" + pattern);
                    sendMessage("GETCLIENTSLIST|Ja");

                    //TODO recieving client list in string
                    try {
                        message = (String) ois.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1"+ message);
//                    String names;
//                    //TODO
//                    names="MOCK";
//                    //TODO
//
//                    String[] namesList;
//                    namesList = names.split("|");
//                    System.out.println("Choose a client:");
//                    for (int i = 0; i < namesList.length; i++) {
//                        System.out.println(i+ ") " + namesList[i]);
//                    }
//
//                    for (int i = 0; i < 111 ; i++) {
//                        sendMessage(i+"");
//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }

                    //                        message = (String) ois.readObject();


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

