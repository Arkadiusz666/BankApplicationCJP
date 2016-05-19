package com.luxoft.CJP.April16.akrzos.bankapp.accounts;

import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.exceptions.OverdraftLimitExceededException;
import com.luxoft.CJP.April16.akrzos.bankapp.serialization.ParsingFeeds;

/**
 * Created by akrzos on 2016-04-12.
 */
public abstract class AbstractAccount implements Account {
//TODO implementign parseFeed
    private float balance;
//    protected static int idCounter=1;
    private int accountID = -1;

    public AbstractAccount(float balance) {
        this.balance = balance;
//        accountId=idCounter;
//        idCounter++;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }



    public float getBalance() {
        return balance;
    }

    public void deposit(float amount) {
        if (amount>0) {
            balance = balance + amount;
        } else
            System.out.println("Unable to deposit negative amount");
    }

    public synchronized void withdraw(float amount) throws OverdraftLimitExceededException {
        if (amount>balance) {
            System.out.println("Insufficient funds!");
            System.out.println("Current balance: " +  balance);
        } else {
            balance = balance - amount;
            System.out.println("Amount " + amount + " withdrawn");
            System.out.println("Current balance: " +  balance);
        }
    }

    public void printReport() {
//        System.out.println("Balance: " + this.getBalance());
        System.out.println(this.toString());
    }

    //TODO hope this is what they meant in the exercise...
    public void decimalValue() {
        System.out.println(Math.round(balance * 100) / 100);
    }

    @Override
    public String toString() {
        return "Saving account{" +
                "balance=" + balance +
                '}';
    }


}


