package com.luxoft.CJP.April16.akrzos.bankapp.networking;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.CheckingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.SavingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Gender;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by arkad_000 on 2016-04-28.
 */
public class BankServer {
    ServerSocket serverSocket;
    Socket connection = null;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    String message;

    Bank bank; //TODO fill with data, check if can be placed elsewhere
    
    void initialize() {
        bank = new Bank("UBS");

        BankServiceImplementation service = new BankServiceImplementation();

        CheckingAccount account1 = new CheckingAccount(10000, 100);
        CheckingAccount account2 = new CheckingAccount(299, 200);
        CheckingAccount account3 = new CheckingAccount(144444);
        SavingAccount account11 = new SavingAccount(131321);
        CheckingAccount account12 = new CheckingAccount(-999, 1000);
        CheckingAccount account13 = new CheckingAccount(-999, 1000);
        CheckingAccount account14 = new CheckingAccount(-999, 1000);
        CheckingAccount account15 = new CheckingAccount(-999, 1000);

        Client client1 = new Client(Gender.MALE, "Arek Krzos", "Krakow");
        Client client2 = new Client(Gender.MALE, "Roman Gabrys", "Krakow");
        Client client3 = new Client(Gender.FEMALE, "Janina Nowak", "Oswiecim");
        Client client4 = new Client(Gender.FEMALE, "Janina Ptak", "Oswiecim");
        Client client5 = new Client(Gender.FEMALE, "Janina Szpak", "Skala");
        Client client6 = new Client(Gender.FEMALE, "Anna Bieda", "Skala");

        service.addAccount(bank, client1,account1);
        service.addAccount(bank, client1,account11);
        service.addAccount(bank, client1,account12);
        service.addAccount(bank, client1,account13);
        service.addAccount(bank, client1,account14);
        service.addAccount(bank, client1,account15);
        service.addAccount(bank, client2,account2);
        service.addAccount(bank, client3,account3);

        service.addClient(bank, client1);
        service.addClient(bank, client2);
        service.addClient(bank, client3);
        service.addClient(bank, client4);
        service.addClient(bank, client5);
        service.addClient(bank, client6);
    }
    
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
            // 4. The two parts communicate via the input and output streams

            BankServerGetResponse responder = new BankServerGetResponse(bank);
            do {
                try {
                    message = (String) ois.readObject();
                    System.out.println(message);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println(responder.getResponse(message));
                    sendMessage(responder.getResponse(message));

            } while (!message.equals("Exit"));
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
        server.initialize();
        while (true) {
            server.run();
        }
    }
}