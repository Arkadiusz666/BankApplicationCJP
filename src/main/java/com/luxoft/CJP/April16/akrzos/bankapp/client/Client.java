package com.luxoft.CJP.April16.akrzos.bankapp.client;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Gender;
import com.luxoft.CJP.April16.akrzos.bankapp.serialization.ParsingFeeds;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by akrzos on 2016-04-12.
 */
public class Client implements ParsingFeeds {
    private static int idCounter=0;
    private int clientId;
    private String name;
    private Set<Account> accounts;
    private Account activeAccount;
    private float initialOverdraft;
    private Gender gender;
    private String city;

        //TODO finish counstructors
    //constructors
    public Client() {
        accounts = new HashSet<Account>();
        clientId=idCounter;
        idCounter++;
    }

    public Client(Gender gender, String name, String city) {
        clientId=idCounter;
        idCounter++;
        this.gender = gender;
        this.name = name;
        initialOverdraft = 0;
        accounts = new HashSet<Account>();
        this.city=city;
        //TODO phone no
        //TODO email adress
    }

    public Gender getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public void setInitialOverdraft(float initialOverdraft) {
        this.initialOverdraft = initialOverdraft;
    }

    public String getName() {
        return name;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public Account getActiveAccount() {
        return activeAccount;
    }

    public float getInitialOverdraft() {
        return initialOverdraft;
    }

    public String getCity() {
        return city;
    }

    public void setActiveAccount(Account account) {
        activeAccount=account;
    }

    public void printReport() {
        System.out.println(this.toString());
        StringBuilder builder = new StringBuilder();
        builder.append("Assets for ");
        builder.append(this.getSalutations());
        builder.append(" ");
        builder.append(this.getName());
        builder.append(" ");
        for (Account account : accounts) {
            builder.append(account.toString());
        }
        System.out.println(builder);
//TODO check this
//        System.out.println("Assets for " + this.getSalutations() + " " +this.getName() + ":");
//        for (Account account : accounts) {
//            account.printReport();
//        }
    }

    public void addAccount(Account account) {
        accounts.add(account);
        activeAccount=account;
    }
    public void removeAccount(Account account) {
        accounts.remove(account);
    }


    private String getSalutations() {
        return this.getGender().getSalutation();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
        //TODO check if ok
    }

    @Override
    public boolean equals(Object client) {
        if (client instanceof Client) {
            if (this.name==((Client) client).getName() && this.gender==((Client) client).getGender()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Id: " +clientId +
                ", name='" + name + '\'' +
                ", accounts=" + accounts +
                ", activeAccount=" + activeAccount +
                ", initialOverdraft=" + initialOverdraft +
                ", gender=" + gender +
                '}';
    }


}


