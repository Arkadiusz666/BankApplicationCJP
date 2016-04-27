package com.luxoft.CJP.April16.akrzos.bankapp;

import com.luxoft.CJP.April16.akrzos.bankapp.accounts.CheckingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.SavingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Gender;
import com.luxoft.CJP.April16.akrzos.bankapp.commands.BankReport;
import com.luxoft.CJP.April16.akrzos.bankapp.exceptions.OverdraftLimitExceededException;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;

/**
 * Created by akrzos on 2016-04-12.
 */
public class BankApplication {

    private static Bank bank;
    private static BankServiceImplementation service;

    private static void printBankReport() {

        bank.printReport();

        Object test;
        test = null;

    }
    private static void initialize() {
        bank = new Bank("UBS");

        service = new BankServiceImplementation();

        CheckingAccount account1 = new CheckingAccount(10000, 100);
        CheckingAccount account2 = new CheckingAccount(299, 200);
        CheckingAccount account3 = new CheckingAccount(144444);
        SavingAccount account11 = new SavingAccount(131321);
        CheckingAccount account12 = new CheckingAccount(-999, 1000);
        CheckingAccount account4 = new CheckingAccount(-100, 1000);



        Client client1 = new Client(Gender.MALE, "Arek Krzos", "Krakow");
        Client client2 = new Client(Gender.MALE, "Roman Gabrys", "Krakow");
        Client client3 = new Client(Gender.FEMALE, "Janina Nowak", "Oswiecim");
        Client client4 = new Client(Gender.FEMALE, "Krol Zebrakow", "Nedza");


        service.addAccount(bank, client1,account1);
        service.addAccount(bank, client1,account11);
        service.addAccount(bank, client1,account12);
        service.addAccount(bank, client2,account2);
        service.addAccount(bank, client3,account3);
        service.addAccount(bank, client4,account4);

        service.addClient(bank, client1);
        service.addClient(bank, client2);
        service.addClient(bank, client3);
        service.addClient(bank, client4);

    }
    private static void modifyBank() throws OverdraftLimitExceededException {

        CheckingAccount account1 = new CheckingAccount(10000, 100);
        CheckingAccount account2 = new CheckingAccount(299, 200);
        CheckingAccount account3 = new CheckingAccount(144444);
        SavingAccount account11 = new SavingAccount(131321);
        CheckingAccount account12 = new CheckingAccount(-999, 1000);
        CheckingAccount account4 = new CheckingAccount(-500, 1000);

        //provide illegal argument to check the exception
        try {
            CheckingAccount account99 = new CheckingAccount(-9999, 1000);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        Client client1 = new Client(Gender.MALE, "Arek Krzos", "Krakow");
        Client client2 = new Client(Gender.MALE, "Roman Gabrys", "Krakow");
        Client client3 = new Client(Gender.FEMALE, "Janina Nowak", "Oswiecim");

        service.addAccount(bank, client1,account1);
        service.addAccount(bank, client1,account11);
        service.addAccount(bank, client1,account12);
        service.addAccount(bank, client2,account2);
        service.addAccount(bank, client3,account3);

        service.addClient(bank, client1);
        service.addClient(bank, client2);
        service.addClient(bank, client3);

        service.removeClient(bank, client3);
        service.removeAccount(bank, client1, account11);

        try {
            account1.withdraw(1);
        } catch (OverdraftLimitExceededException e) {
            e.printStackTrace();
        }

        try {
            account1.withdraw(1000000);
        } catch (OverdraftLimitExceededException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws OverdraftLimitExceededException {
        initialize();
//        modifyBank();
//        printBankReport();
        for (String arg : args) {
            if (arg.equals("-report")) {
                System.out.println("----------------------------------------");
                System.out.println("BANK REPORT:");
                BankReport report = new BankReport();
                report.getNumberOfClients(bank);
                report.getAccountsNumber(bank);
                report.getBankCreditSum(bank);
                report.getClientsByCity(bank);
                report.getClientsSorted(bank);
            }
        }

    }
}


