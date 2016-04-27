package com.luxoft.CJP.April16.akrzos.bankapp.services;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;

/**
 * Created by akrzos on 2016-04-12.
 */
public interface BankService {
    void addClient(Bank bank, Client client);
    void removeClient(Bank bank, Client client);
    void addAccount (Bank bank, Client client, Account account);
    void removeAccount (Bank bank, Client client, Account account);
    Client getClient(Bank bank, String clientName);

}


