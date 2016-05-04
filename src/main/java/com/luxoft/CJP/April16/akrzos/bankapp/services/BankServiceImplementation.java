package com.luxoft.CJP.April16.akrzos.bankapp.services;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.exceptions.OverdraftLimitExceededException;

import java.io.*;
import java.util.stream.Stream;

/**
 * Created by akrzos on 2016-04-12.
 */
public class BankServiceImplementation implements BankService {

    public void addClient(Bank bank, Client client) {
        bank.addClient(client);
        bank.getClientsByName().put(client.getName(), client);
    }

    public void removeClient(Bank bank, Client client) {
        bank.removeClient(client);
        //System.out.println("Client removed from the bank");

    }

    public void addAccount(Bank bank, Client client, Account account) {
        //TODO browse bank for users
        client.addAccount(account);
        client.setActiveAccount(account);
        //System.out.println("Account added");
    }

    public void removeAccount(Bank bank, Client client, Account account) {
        //TODO browse bank for users
        client.removeAccount(account);
        //System.out.println("Konto usuniete");
    }
//    public void saveClientToStream(Client client, ObjectOutputStream oos) {
//        //TODO
//        todo
//    }
//    public void loadClientFromStream(Client client, ObjectOutputStream oos) {
//        //TODO
//        todo
//    }

    public void saveClient(Client client) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
//            fos = new FileOutputStream(client.getName()+".client");
            fos = new FileOutputStream("test.client");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.writeObject(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client loadClient() {
        FileInputStream fis = null;
        Client tempClient = null;
        try {
            fis = new FileInputStream("test.client"); //TODO files in format *.client
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream ois=null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            tempClient=(Client) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO delete client file
        return tempClient;
    }

    public Client getClient(Bank bank, String clientName) {
        return null;
    }


}


