package com.luxoft.CJP.April16.akrzos.bankapp.commands;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.CheckingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.SavingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Gender;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by arkad_000 on 2016-04-18.
 */
class BankCommander {
    public static Bank currentBank;
    public static Client currentClient;
    static Map<String, Command> commandsMap;

    public BankCommander() {
        commandsMap = new TreeMap<String, Command>();
        commandsMap.put("Find Client", new FindClientCommand());
        commandsMap.put("Get Accounts", new GetAccountsCommand());
        commandsMap.put("Deposit funds", new DepositCommand());
        commandsMap.put("Transfer funds", new TransferCommand());
        commandsMap.put("Withdraw funds", new WithdrawCommand());
        commandsMap.put("Select bank", new SelectBankCommand());
        commandsMap.put("Reporting", new BankReport());
        commandsMap.put("Delete active user", new DeleteActiveUserCommand());
        commandsMap.put("Exit", new ExitCommand());
    }
//REDUNDANT SINCE INTRODUCTION OF DATABASES
//    public void initialize() {
//        currentBank = new Bank("UBS");
//
//        BankServiceImplementation service = new BankServiceImplementation();
//
//        CheckingAccount account1 = new CheckingAccount(10000, 100);
//        CheckingAccount account2 = new CheckingAccount(299, 200);
//        CheckingAccount account3 = new CheckingAccount(144444);
//        SavingAccount account11 = new SavingAccount(131321);
//        CheckingAccount account12 = new CheckingAccount(-999, 1000);
//
//        Client client1 = new Client(Gender.MALE, "Arek Krzos", "Krakow");
//        Client client2 = new Client(Gender.MALE, "Roman Gabrys", "Krakow");
//        Client client3 = new Client(Gender.FEMALE, "Janina Nowak", "Oswiecim");
//        Client client4 = new Client(Gender.FEMALE, "Janina Ptak", "Oswiecim");
//        Client client5 = new Client(Gender.FEMALE, "Janina Szpak", "Skala");
//
//        service.addAccount(currentBank, client1,account1);
//        service.addAccount(currentBank, client1,account11);
//        service.addAccount(currentBank, client1,account12);
//        service.addAccount(currentBank, client2,account2);
//        service.addAccount(currentBank, client3,account3);
//
//        service.addClient(currentBank, client1);
//        service.addClient(currentBank, client2);
//        service.addClient(currentBank, client3);
//        service.addClient(currentBank, client4);
//        service.addClient(currentBank, client5);
//
//
//    }
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Active client: ");
            if (currentClient==null) {
                System.out.println("no client selected");
            } else {
                System.out.println(currentClient.toString());
            }

            System.out.print("Active bank: ");
            if (currentBank==null) {
                System.out.println("no bank selected");
            } else {
                System.out.println(currentBank.getName());
            }
            System.out.println("----------------------------");
            for (String s : commandsMap.keySet()) {
                System.out.println("-" + s);
            }

            boolean found=false;
            while (!found) {
                String command = scanner.nextLine();
                for (String s : commandsMap.keySet()) {
                    if (s.equals(command)) {
                        commandsMap.get(s).execute();
                        found=true;
                    }
                }
                if (!found) {
                    System.out.println("Incorrect command! Try again.");
                }
            }
        }
    }

    public void registerCommand(String name, Command command) {
        commandsMap.put(name, command);
    }
    public void removeCommand(String name) {
        commandsMap.remove(name);
    }

    public static void main(String args[]) {
        BankCommander start = new BankCommander();
        start.execute();

    }
}

