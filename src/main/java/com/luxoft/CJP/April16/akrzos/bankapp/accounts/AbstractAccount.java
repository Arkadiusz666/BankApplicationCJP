package com.luxoft.CJP.April16.akrzos.bankapp.accounts;

import com.luxoft.CJP.April16.akrzos.bankapp.exceptions.OverdraftLimitExceededException;
import com.luxoft.CJP.April16.akrzos.bankapp.serialization.ParsingFeeds;

/**
 * Created by akrzos on 2016-04-12.
 */
public abstract class AbstractAccount implements Account, ParsingFeeds {

    private float balance;

    public AbstractAccount(float balance) {
        this.balance = balance;
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

    public void withdraw(float amount) throws OverdraftLimitExceededException {
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


