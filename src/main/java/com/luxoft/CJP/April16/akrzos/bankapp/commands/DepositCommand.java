package com.luxoft.CJP.April16.akrzos.bankapp.commands;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;

import java.util.Scanner;

/**
 * Created by arkad_000 on 2016-04-18.
 */
public class DepositCommand implements Command {

    public void execute() {
        HelperCommand helper = new HelperCommand();
        Account tempAccount;
        printCommandInfo();

        if (BankCommander.currentClient==null) {
            System.out.println("No user selected - find user first!");

        } else {
            if (BankCommander.currentClient.getAccounts().size()==0) {
                System.out.println("Current client has no accounts");
            } else {

                tempAccount = helper.chooseAccount(BankCommander.currentClient.getAccounts());
                float tempAmount=0;
                while (tempAmount<=0) {
                    System.out.println("Provide positive amount to deposit:");
                    Scanner scanner = new Scanner(System.in);

                    tempAmount = scanner.nextFloat();
                }
                tempAccount.deposit(tempAmount);



//                    if (tempAmount>0) {
//                        //TODO make a deposit, change to float
//                        BankCommander.currentClient.getAccounts().get(tempIndex).deposit(tempAmount);
//                    } else {
//                        System.out.println("Provide positive value!");
//                    }

            }

        }



    }

    public void printCommandInfo() {
        System.out.println("Deposit - Allows depositing funds to the account specified");

    }
}

