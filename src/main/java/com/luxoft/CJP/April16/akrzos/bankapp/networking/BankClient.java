package com.luxoft.CJP.April16.akrzos.bankapp.networking;

        import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
        import com.luxoft.CJP.April16.akrzos.bankapp.helpers.Helper;

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
public class BankClient extends Thread{
    protected Socket requestSocket;
    protected ObjectOutputStream oos;
    protected ObjectInputStream ois;
    protected String message;
    protected static final String SERVER = "localhost";
    protected List<String> commandList;
    protected String activeClient;
    protected Scanner scanner = new Scanner(System.in);

    public BankClient() {
        commandList = new ArrayList<String>();
        commandList.add("Set active client");
        commandList.add("Withdraw");
        commandList.add("Set active account");
        commandList.add("Exit");
        activeClient=null;
    }

    public void run() {
        try {
            // 1. creating a socket to connect to the server
            requestSocket = new Socket(SERVER, 2002);
            System.out.println("Connected to localhost is port 2002");
            // 2. get Input and Output streams
            oos = new ObjectOutputStream(requestSocket.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(requestSocket.getInputStream());
            // 3: Communicating with the server

            clientLoop();

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

    //TODO test

    public void clientLoop() {
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
                searchClient(); //0
            }
            if (choice==1) {
                withdrawFromActiveClient(); //1
            }
            if (choice==2) {
                setActiveAccount();//2
            }
            if (choice==3) {
                closeConnection();//3
                break;
            }
        }
    }

    public void closeConnection() {
        sendMessage("EXIT");
        System.out.println("Connection closed");
        //TODO
    }

    public void sendMessage(final String msg) {
        try {
            oos.writeObject(msg);
            oos.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void searchClient() {
        System.out.println("Provide client name:");
        String pattern;
        scanner.nextLine(); //scanning int did not go to next line
        pattern = scanner.nextLine();

        sendMessage("GETCLIENTSLIST|" + pattern);

        message=waitForResponse();
        message=Helper.splitAndChoose(message, "RESPONSE");
        System.out.println(message);
        activeClient=message;
    }

    public void setActiveAccount() {
        if (activeClient==null) {
            System.out.println("No active client set!");
        } else {
            sendMessage("GETACCOUNTSLIST|" + activeClient);
            message=waitForResponse();
            if (!message.equals("ACCOUNTSLIST")) {
                sendMessage("SETACTIVEACCOUNT|" + activeClient + "|" + Helper.splitAndChoose(message, "ACCOUNTSLIST\\|"));
                message=waitForResponse();
                System.out.println(message);
            } else {
                System.out.println("Client has no accounts");
            }
            Helper.pressAnyKeyToContinue();
        }
    }

    public void withdrawFromActiveClient() {
        if (activeClient==null) {
            System.out.println("No active client set!");
        } else {
            System.out.println("Provide an amount to withdraw:");
            int amount = scanner.nextInt();
            withdrawFromActiveClient(amount);
        }
    }
    public void withdrawFromActiveClient(int amount) {
        sendMessage("WITHDRAW|"+activeClient+"|"+amount);
        message=waitForResponse();
        System.out.println(message);
//        Helper.pressAnyKeyToContinue();
    }
    public String waitForResponse() {
        String message="";
        do {
            try {
                message = (String) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                
            }
        } while (message.length()<1);
        //TODO TEST
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //TODO TEST
        return message;
    }
    public static void main(final String args[]) {
        BankClient client = new BankClient();
        client.run();
    }
}

