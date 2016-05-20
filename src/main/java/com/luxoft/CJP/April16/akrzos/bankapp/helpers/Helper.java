package com.luxoft.CJP.April16.akrzos.bankapp.helpers;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.CheckingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.SavingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Gender;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;

import java.util.*;

/**
 * Created by arkad_000 on 2016-05-02.
 */
 public class Helper {
    public static void pressAnyKeyToContinue() {
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    public static String splitAndChoose(String delimitedString, String toBeIgnored) {
        String message;
        Scanner scanner = new Scanner(System.in);
        message = delimitedString.replaceAll(toBeIgnored, "");
        String[] messageList = message.split("\\|");
        System.out.println("Choose one:");
        if (messageList.length < 1 || messageList[0].equals("")) {
            return "No item found";
        } else {
            for (int i = 0; i < messageList.length; i++) {
                System.out.println(i + ") " + messageList[i]);
            }
            while (true) {
                int choice = scanner.nextInt(); //TODO
                if (choice < 0 || choice >= messageList.length) {
                    System.out.println("Incorrect input");
                } else {
                    return messageList[choice];
                }
            }
        }
    }

    public static Bank generateBank() {
        Bank bank = new Bank("UBS");

        BankServiceImplementation service = new BankServiceImplementation();

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


        service.addAccount(bank, client1, account1);
        service.addAccount(bank, client1, account11);
        service.addAccount(bank, client1, account12);
        service.addAccount(bank, client2, account2);
        service.addAccount(bank, client3, account3);
        service.addAccount(bank, client4, account4);

        service.addClient(bank, client1);
        service.addClient(bank, client2);
        service.addClient(bank, client3);
        service.addClient(bank, client4);

        return bank;
    }

    public static boolean areYouSure() {
        Boolean decision;
        System.out.println("Are you sure? (Y/N)");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String searchString = scanner.nextLine().trim();
            if (searchString.matches("y|Y")) {
                return true;
            }
            if (searchString.matches("n|N")) {
                return false;
            }
            System.out.println("Incorrect input");
        }
    }
}
