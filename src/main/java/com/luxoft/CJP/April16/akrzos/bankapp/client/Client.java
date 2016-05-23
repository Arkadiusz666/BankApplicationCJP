package com.luxoft.CJP.April16.akrzos.bankapp.client;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.CheckingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.SavingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Gender;
import com.luxoft.CJP.April16.akrzos.bankapp.database.annotation.NoDB;
import com.luxoft.CJP.April16.akrzos.bankapp.serialization.ParsingFeeds;

import java.io.Serializable;
import java.util.*;

/**
 * Created by akrzos on 2016-04-12.
 */
public class Client implements ParsingFeeds, Serializable {
    private static final long serialVersionUID = 4020000988842999258L;
    private int clientId = -1;

    private String name;
    @NoDB
    private Set<Account> accounts; //TODO check equality of accounts and properties of the set
    private Account activeAccount;
    @NoDB private float initialOverdraft;
    private Gender gender;
    private String city;

    //constructors
    public Client() {
        accounts = new HashSet<Account>();
    }

    public Client(Gender gender, String name, String city) {
//        clientId=idCounter;
//        idCounter++;
        this.gender = gender;
        this.name = name;
        initialOverdraft = 0;
        accounts = new HashSet<Account>();
        this.city=city;
        //TODO phone no
        //TODO email adress
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public void setGender(String gender) {
        if (gender.matches("m|M|male|Male|MALE")) {
            this.setGender(Gender.MALE);
        }
        if (gender.matches("f|F|female|Female|FEMALE")) {
            this.setGender(Gender.FEMALE);
        }
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
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
                "name='" + name + '\'' +
                ", accounts=" + accounts +
                ", activeAccount=" + activeAccount +
                ", initialOverdraft=" + initialOverdraft +
                ", gender=" + gender +
                '}';
    }

    public void parseFeed(Map<String, String> feed) {
        Account tempAccount;
        //TODO - check that client have max 1 of each account and confirm if that makes any sense
        if (feed.get("accounttype").equals("c")) {
            tempAccount=new CheckingAccount(Float.parseFloat(feed.get("balance")), Float.parseFloat(feed.get("overdraft")));
            this.addAccount(tempAccount);
        }
        if (feed.get("accounttype").equals("s")) {
            tempAccount=new SavingAccount(Float.parseFloat(feed.get("balance")));
            this.addAccount(tempAccount);
        }
        //TODO calling parse account seems to be making no sense at this point
    }
}


