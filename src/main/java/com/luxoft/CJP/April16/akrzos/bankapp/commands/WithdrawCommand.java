package com.luxoft.CJP.April16.akrzos.bankapp.commands;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.exceptions.OverdraftLimitExceededException;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;

import java.util.Scanner;

/**
 * Created by arkad_000 on 2016-04-18.
 */
public class WithdrawCommand implements Command {

    public void execute() {
        printCommandInfo();
        Scanner scanner = new Scanner(System.in);
        if (BankCommander.currentClient==null) {
            System.out.println("Current client not set!");
            new FindClientCommand().execute();
        }
        if (BankCommander.currentClient.getActiveAccount()==null) {
            HelperCommand helper = new HelperCommand();
            Account tempAccount = helper.chooseAccount(BankCommander.currentClient.getAccounts());
            BankCommander.currentClient.setActiveAccount(tempAccount);
        }

        int amount = 0;
        while (amount<=0) {
            System.out.println("Provide an amount to withdraw:");
            amount = scanner.nextInt();
        }
        try {
            BankCommander.currentClient.getActiveAccount().withdraw(amount);
        } catch (OverdraftLimitExceededException e) {
            e.printStackTrace();
        }
    }

    public void printCommandInfo() {
        System.out.println("Withdraw - Allows withdrawal of assets from the account");
    }
}

