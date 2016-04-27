package com.luxoft.CJP.April16.akrzos.bankapp.commands;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;

/**
 * Created by arkad_000 on 2016-04-18.
 */
public class GetAccountsCommand implements Command {


    public void execute() {
        printCommandInfo();
        if (BankCommander.currentClient==null) {
            System.out.println("No active client set!");
        } else {
            BankCommander.currentClient.printReport();
        }
    }

    public void printCommandInfo() {
        System.out.println("Get Accounts - Shows accounts of the client");
    }
}

