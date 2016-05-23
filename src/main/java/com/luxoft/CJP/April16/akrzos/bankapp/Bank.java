package com.luxoft.CJP.April16.akrzos.bankapp;

/**
 * Created by akrzos on 2016-04-13.
 */
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Gender;
import com.luxoft.CJP.April16.akrzos.bankapp.database.annotation.NoDB;
import com.luxoft.CJP.April16.akrzos.bankapp.exceptions.OverdraftLimitExceededException;
import com.luxoft.CJP.April16.akrzos.bankapp.listeners.ClientRegistrationListener;
import com.luxoft.CJP.April16.akrzos.bankapp.listeners.EmailNotificationListener;
import com.luxoft.CJP.April16.akrzos.bankapp.listeners.PrintClientListener;
import com.luxoft.CJP.April16.akrzos.bankapp.serialization.ParsingFeeds;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;

import java.util.*;

/**
 * Created by akrzos on 2016-04-12.
 */
public class Bank implements ParsingFeeds{
    private String name;
//    private List<Client> clients; //deprecated in EX5
    private Set<Client> clients;
    @NoDB private ClientRegistrationListener[] listeners = {new PrintClientListener(), new EmailNotificationListener(), new PrintClientListener()};
    @NoDB private Map<String, Client> clientsByName;
//    private static int idCounter=0;
    private int bankId = -1;

    public Bank(String name) {
        this.name=name;
//        clients = new LinkedList<Client>(); //deprecated in EX5
        clients = new HashSet<Client>();
        clientsByName = new HashMap<String, Client>();
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public int getBankId() {
        return bankId;
    }

    public void printReport() {
        System.out.println("Bank assets:");
        for (Client client : clients) {
            client.printReport();
        }
    }
    public void addClient(Client client) {
        for (ClientRegistrationListener listener : listeners) {
            listener.onClientAdded(client);
        }
        clients.add(client);

    }
    public void removeClient(Client client) {
        clients.remove(client);
    }
    //TODO not sure if safe


    public Set<Client> getClients() {
        return clients;
    }

    public Map<String, Client> getClientsByName() {
        return clientsByName;
    }

    public void transferFunds(Float amount, Account accountFrom, Account accountTo) {
        try {
            accountFrom.withdraw(amount);
            accountTo.deposit(amount);
        } catch (OverdraftLimitExceededException e) {
            e.printStackTrace();
        }
    }

    public void parseFeed(Map<String, String> feed) {
        BankServiceImplementation service = new BankServiceImplementation();
        Client tempClient;

        //adding client if not present in the bank
        if (!clientsByName.containsKey(feed.get("name"))) {
            Gender tempGender= Gender.MALE;
            if (feed.get("gender").equals("f")) {
                tempGender=Gender.FEMALE;
            }
            tempClient = new Client(tempGender, feed.get("name"), feed.get("city"));//TODO WTF happened with the City?
            service.addClient(this, tempClient);
        } else {
            tempClient=clientsByName.get(feed.get("name"));
        }
        tempClient.parseFeed(feed); //processing account data


    }
}



