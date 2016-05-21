package com.luxoft.CJP.April16.akrzos.bankapp.commands;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.CheckingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.SavingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Gender;
import com.luxoft.CJP.April16.akrzos.bankapp.database.ClientDAOImplementation;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.ClientNotFoundException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
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
        if (BankCommander.currentBank==null) {
            System.out.println("No banks selected!");
            new SelectBankCommand().execute();
        }
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
//        System.out.println("Initial overdraft:");
//        scanner.nextLine(); //TODO does not work!!!! (because init overdraft is not saved in db)
//        tempInitialOverdraft=scanner.nextInt();

        System.out.println("City:");
        scanner.nextLine(); //TODO check if working
        tempCity=scanner.nextLine(); //TODO check if working
        Client tempClient = new Client(tempGender, tempName, tempCity);
//        tempClient.setInitialOverdraft(tempInitialOverdraft);
        tempClient.addAccount(new CheckingAccount(0,tempClient.getInitialOverdraft()));
        tempClient.addAccount(new SavingAccount(0));

        BankCommander.currentBank.addClient(tempClient);
//        BankCommander.currentClient.addAccount(new SavingAccount(0));//TODO it adds abstract???
        System.out.println("New user successfully created:");

        ClientDAOImplementation clientDAO = new ClientDAOImplementation();
        try {
            clientDAO.save(tempClient, BankCommander.currentBank);
            BankCommander.currentClient = clientDAO.findClientByName(BankCommander.currentBank, tempClient.getName());
        } catch (DAOException | ClientNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(tempClient.toString());
        System.out.println(BankCommander.currentBank.getClients().toString());
    }

    public void printCommandInfo() {
        System.out.println("Add Client -Allows adding a client to the system");
    }
}

