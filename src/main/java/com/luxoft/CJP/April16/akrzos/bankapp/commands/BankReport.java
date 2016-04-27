package com.luxoft.CJP.April16.akrzos.bankapp.commands;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.commands.BankCommander;
import com.luxoft.CJP.April16.akrzos.bankapp.commands.Command;

import java.util.*;

/**
 * Created by arkad_000 on 2016-04-25.
 */
public class BankReport implements Command{
    public void getNumberOfClients(Bank bank) {
        int counter = 0;
        for (Client client : bank.getClients()) {
            counter++;
        }
        System.out.println("Number of Clients: "+ counter);
    }
    public void getAccountsNumber(Bank bank) {
        int counter = 0;

        for (Client client : bank.getClients()) {
            counter+=client.getAccounts().size();
        }
        System.out.println("Number of accounts in the bank: "+ counter);
    }
    public void getClientsSorted(Bank bank) {
        Set<String> names = new TreeSet<String>();
        for (Client client : bank.getClients()) {
            names.add(client.getName());
        }
        for (String name : names) {
            System.out.println(name);
        }
    }
    public void getBankCreditSum(Bank bank) {//TODO i hope i understood this correctly...
        float debit = 0;
        for (Client client : bank.getClients()) {
            for (Account account : client.getAccounts()) {
                if (account.getBalance()<0) {
                    debit+=account.getBalance();
                }
            }
        }
        System.out.println("Total debit for all clients: " + -debit);
    }
    public void getClientsByCity(Bank bank) {
        Map<String, List<Client>> cityClientMap = new TreeMap<String, List<Client>>();
        for (Client client : bank.getClients()) {
            if (cityClientMap.containsKey(client.getCity())) { //if city is listed,
                cityClientMap.get(client.getCity()).add(client);//add client to the list in the value
            } else {
                cityClientMap.put(client.getCity(), new LinkedList<Client>());
                cityClientMap.get(client.getCity()).add(client);
            }
        }
        for (String s : cityClientMap.keySet()) {
            System.out.print(s + ": ");
            for (Client client : cityClientMap.get(s)) {
                System.out.print(client.getGender().getSalutation()+ client.getName() + "  ");

            }
            System.out.println();
        }
    }

    public void execute() {
        printCommandInfo();
        getNumberOfClients(BankCommander.currentBank);
        getAccountsNumber(BankCommander.currentBank);
        getClientsSorted(BankCommander.currentBank);
        getBankCreditSum(BankCommander.currentBank);
        getClientsByCity(BankCommander.currentBank);

    }

    public void printCommandInfo() {
        System.out.println("General repoting solutions:");
    }
}
