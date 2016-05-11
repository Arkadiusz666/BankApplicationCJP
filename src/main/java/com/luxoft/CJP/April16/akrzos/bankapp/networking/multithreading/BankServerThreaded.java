package com.luxoft.CJP.April16.akrzos.bankapp.networking.multithreading;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by akrzos on 2016-05-09.
 */
public class BankServerThreaded {
    final int POOL_SIZE = 10;

//    ServerSocket serverSocket = new ServerSocket(PORT);
    ExecutorService pool = Executors.newFixedThreadPool(500);
    ServerSocket serverSocket;
    Socket connection = null;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    BankServiceImplementation service = new BankServiceImplementation();
    Bank bank;

    void initialize() {
        bank = new Bank("UBS");

        BankServiceImplementation service = new BankServiceImplementation();

        CheckingAccount account1 = new CheckingAccount(10000, 100);
        CheckingAccount account2 = new CheckingAccount(299, 200);
        CheckingAccount account3 = new CheckingAccount(144444);
        SavingAccount account11 = new SavingAccount(131321);
        CheckingAccount account12 = new CheckingAccount(999, 1000);
        CheckingAccount account13 = new CheckingAccount(10000, 0);
        CheckingAccount account14 = new CheckingAccount(999, 10000);
        CheckingAccount account15 = new CheckingAccount(-999, 10000);

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
        service.addAccount(bank, client4,account14);
        service.addAccount(bank, client5,account15);
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
            serverSocket = new ServerSocket(2002, 10);

            System.out.println("Waiting for connection");

                while (true) { //TODO should have flag???
                    connection = serverSocket.accept();
                    pool.execute(new ServerThread(connection, bank, service));
                }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            // 4: Closing connection
            try {
                serverSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void main(final String args[]) {
        BankServerThreaded server = new BankServerThreaded();
        server.initialize();

        while (true) {
            //TODO - check if the loop is needed
            server.run();
        }
    }

}
