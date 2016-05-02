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
    private String activeClient;
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
                while(true) {
                    System.out.print("Active client: ");
                    if (activeClient==null) {
                        System.out.println("not set");
                    } else {
                        System.out.println(activeClient);
                    }
                    for (int i = 0; i < commandList.size(); i++) {
                        System.out.println(i+ ") " + commandList.get(i));
                    }

                    int choice = scanner.nextInt(); //TODO better choice options
                    if (choice==0) {
                        searchClient(); //1
                    }
                    if (choice==1) {
                        if (activeClient==null) {
                            System.out.println("No active client set!");
                        } else {
                            System.out.println("Provide an amount to withdraw:");
                            int amount = scanner.nextInt();
                            sendMessage("WITHDRAW|"+activeClient+"|"+amount);
                            do {
                                try {
                                    message = (String) ois.readObject();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Message here "+ message);
                            } while (message.length()<1);
                            //TODO Test


                        }

                    }



                    System.out.println("1"+ message); //TODO TEST
                }
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

    void searchClient() {
        //TODO 1, asking for client list
        System.out.println("Provide client name:");
        String pattern;
        pattern = scanner.nextLine(); //TODO why do I need two of those???
        pattern = scanner.nextLine(); //TODO

        sendMessage("GETCLIENTSLIST|" + pattern);
        do {
            try {
                message = (String) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } while (!message.matches("RESPONSE.*"));
        message=message.substring(8);
        if (message.length()>0) {
            String[] namesList;
            namesList = message.split("\\|");
            System.out.println("Choose a client:");
            for (int i = 0; i < namesList.length; i++) {
                System.out.println(i+ ") " + namesList[i]);
            }


            while (true) {
                int choice = scanner.nextInt(); //TODO
                if (choice<0||choice>=namesList.length) {
                    System.out.println("Incorrect input");
                } else {
                    activeClient=namesList[choice];
                    break;
                }
            }
        } else {
            System.out.println("User not found");
        }
    }

    public static void main(final String args[]) {
        BankClient client = new BankClient();
        client.run();
    }

}

