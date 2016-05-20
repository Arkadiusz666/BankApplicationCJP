package com.luxoft.CJP.April16.akrzos.bankapp.commands;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.helpers.Helper;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by arkad_000 on 2016-04-18.
 */
public class FindClientCommand implements Command {

    public void execute() {
        if (BankCommander.currentBank==null) {
            System.out.println("No bank selected!");
            Helper.pressAnyKeyToContinue();
            new SelectBankCommand().execute();
        }
        Scanner scanner = new Scanner(System.in);

        printCommandInfo();
        //TODO searching - find by name, put into array and allow to chose by inputting index
        System.out.println("Name of the person you are looking for:");
        String searchString =scanner.nextLine().trim();
        List<Client> foundClientList= new ArrayList<Client>();

        //searching for users maching the criteria -deprecated since Ex5 - need to use map instead
//        for (Client client : BankCommander.currentBank.getClients()) {
//            if (client.getName().matches((".*"+searchString+".*"))) {
//                foundClientList.add(client);
//            }
//        }
        for (String s : BankCommander.currentBank.getClientsByName().keySet()) {
            if (s.matches(".*"+searchString+".*")) {
                foundClientList.add(BankCommander.currentBank.getClientsByName().get(s));
            }
        }


        System.out.println("List of the users matching the criteria:");
            //TODO if no found
//        System.out.println("No users found");
        for (int i = 0; i < foundClientList.size(); i++) {
            System.out.println(i + ") " + foundClientList.get(i).getGender().getSalutation() + foundClientList.get(i).getName());
        }
        int temp = scanner.nextInt();
        if ((foundClientList.size()>temp)&&(temp>=0)) {
            BankCommander.currentClient=foundClientList.get(temp);
            System.out.println("Current client set: " + foundClientList.get(temp));
        }
        Helper.pressAnyKeyToContinue();
    }

    public void printCommandInfo() {
        System.out.println("Find Client - Searches the bank for a customer specified");
    }
}

