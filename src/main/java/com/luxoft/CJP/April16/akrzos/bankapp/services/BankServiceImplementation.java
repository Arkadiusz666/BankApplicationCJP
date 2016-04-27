package com.luxoft.CJP.April16.akrzos.bankapp.services;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.exceptions.OverdraftLimitExceededException;

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

    public Client getClient(Bank bank, String clientName) {
        return null;
    }


}


