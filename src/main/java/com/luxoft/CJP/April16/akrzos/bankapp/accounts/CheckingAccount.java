package com.luxoft.CJP.April16.akrzos.bankapp.accounts;

import com.luxoft.CJP.April16.akrzos.bankapp.accounts.AbstractAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.exceptions.OverdraftLimitExceededException;

/**
 * Created by akrzos on 2016-04-12.
 */
public class CheckingAccount extends AbstractAccount {
    private float overdraft;

    public CheckingAccount(float balance) {
        super(balance);
        if(balance<0) throw new IllegalArgumentException();
        overdraft = 0;
//        accountId=idCounter;
//        idCounter++;
    }

    public CheckingAccount(float balance, float overdraft) {
        super(balance);
        this.overdraft = overdraft;
        if(balance<-overdraft) throw new IllegalArgumentException();
//        accountId=idCounter;
//        idCounter++;
    }
    public String getAccountType() {
        return "C";
    }

    public void setOverdraft(float overdraft) {
        this.overdraft = overdraft;
    }

    public float getOverdraft() {
        return overdraft;
    }
    public float getMaxAmountToWithdraw() {
        return super.getBalance()+overdraft;
    }

    @Override
    public void withdraw(float amount) throws OverdraftLimitExceededException{
        if (amount>(super.getBalance()+overdraft)) {
            throw new OverdraftLimitExceededException(amount, this); //TODO
            //System.out.println("Insufficient funds!");
        } else
        {
            super.withdraw(amount);
            System.out.println("");
        }
    }



    @Override
    public void printReport() {
//        System.out.println("Balance: " + this.getBalance() + " ; Overdraft limit: " + this.getOverdraft());
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "overdraft=" + overdraft +
                ", balance=" + super.getBalance() +
                '}';
    }
}


