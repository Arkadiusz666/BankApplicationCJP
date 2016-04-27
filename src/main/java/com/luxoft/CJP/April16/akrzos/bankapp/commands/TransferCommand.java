package com.luxoft.CJP.April16.akrzos.bankapp.commands;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;

import java.util.Scanner;

/**
 * Created by arkad_000 on 2016-04-18.
 */
public class TransferCommand implements Command {
    Client tempClient;
    Client clientFrom;
    Client clientTo;

    public void execute() {
        printCommandInfo();

        Scanner scanner = new Scanner(System.in);

        tempClient = BankCommander.currentClient;

        System.out.println("Find a client to transfer from:");
        new FindClientCommand().execute();
        clientFrom=BankCommander.currentClient;

        System.out.println("Find a client to transfer to:");
        new FindClientCommand().execute();
        clientTo=BankCommander.currentClient;

        System.out.println("How much would you like to transfer from " + clientFrom.getName() + " to " + clientTo.getName());
        BankCommander.currentBank.transferFunds((float)scanner.nextInt(),clientFrom.getActiveAccount(),clientTo.getActiveAccount());
        System.out.println("Funds transferred");
        BankCommander.currentClient=tempClient; //Set back current client

    }

    public void printCommandInfo() {
        System.out.println("Transfer - Allows transferring assets between active account of current client and chosen client");

    }
}

