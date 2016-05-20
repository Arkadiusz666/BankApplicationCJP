package com.luxoft.CJP.April16.akrzos.bankapp.commands;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by arkad_000 on 2016-04-25.
 */
public class HelperCommand {
    private int counter;
    private int chosen;
    Scanner scanner = new Scanner(System.in);
    // TODO assuming set is not empty - check if validation in place
    public Account chooseAccount(Set<Account> set) {
        counter=0;
        Account tempAccount = null;//TODO check if null is acceptable here

        for (Account account : set) {
            System.out.println(counter+") "+account);
            counter++;
        }
        while (chosen<0||chosen>=counter) {
            System.out.println("Choose an account: ");
            chosen=scanner.nextInt();
        }
        counter=0;
        for (Account account : set) {
            if (chosen==counter) {
                tempAccount=account;
            }
        }
        return tempAccount;
    }
    public Client chooseClient(Set<Client> set) {
        counter=0;
        Client tempClient = null;//TODO check if null is acceptable here

        for (Client client : set) {
            System.out.println(counter+") "+client.getGender().getSalutation() + " " + client.getName());
            counter++;
        }
        while (chosen<0||chosen>=counter) {
            System.out.println("Choose a client: ");
            chosen=scanner.nextInt();
        }
        counter=0;
        for (Client client : set) {
            if (chosen==counter) {
                tempClient=client;
            }
        }
        return tempClient;
    }
    public Bank chooseBank(ArrayList<Bank> list) {
        Scanner scanner = new Scanner(System.in);

        if (list.size()==0) {
            System.out.println("No items to choose from");
            return null;
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i+ ") " +list.get(i).getName());
            }
        }
        int chosen = -1;
        while (chosen<0||chosen>=list.size()) {
            System.out.println("Choose one: ");
            chosen=scanner.nextInt();
        }
        return list.get(chosen);
    }

}
