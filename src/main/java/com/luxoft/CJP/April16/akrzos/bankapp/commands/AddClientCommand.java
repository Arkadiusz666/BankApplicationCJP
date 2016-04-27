package com.luxoft.CJP.April16.akrzos.bankapp.commands;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.SavingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Gender;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankService;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;

import java.util.Scanner;

/**
 * Created by arkad_000 on 2016-04-18.
 */
public class AddClientCommand implements Command {
    float tempInitialOverdraft;
    String tempName;
    String tempCity;
    Gender tempGender;
    String temp;

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        printCommandInfo();
        System.out.println("Full name:");
        tempName=scanner.nextLine(); //TODO validation?
        System.out.println("Gender: (M or F)");

        while (true) {
            temp = scanner.next();
            if (temp.matches("[Mm]")) {
                tempGender =Gender.MALE;
                System.out.println("Male");
                break;
            } else if (temp.matches("[Ff]")) {
                tempGender =Gender.FEMALE;
                System.out.println("Female");
                break;
            } else System.out.println("Incorrect input, try again (M of F)");
        }
        System.out.println("Initial overdraft:");
        tempInitialOverdraft=scanner.nextFloat();

        System.out.println("City:");
        tempCity=scanner.nextLine(); //TODO check if working
        Client tempClient = new Client(tempGender, tempName, tempCity);
        tempClient.setInitialOverdraft(tempInitialOverdraft);

        BankCommander.currentBank.addClient(tempClient);
        BankCommander.currentClient=tempClient;
        BankCommander.currentClient.addAccount(new SavingAccount(0));//TODO it adds abstract???
        System.out.println("New user successfully created:");
        System.out.println(tempClient.toString());
        System.out.println(BankCommander.currentBank.getClients().toString());
    }

    public void printCommandInfo() {
        System.out.println("Add Client - Allows adding a client to the system");
    }
}

