package com.luxoft.CJP.April16.akrzos.bankapp.networking;

import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by arkad_000 on 2016-04-28.
 */
public class BankClientMenu {
    private List<String> commandList;
    private List<Client> downloadedClientList;//TODO download the list
    private Client activeClient;

    Scanner scanner = new Scanner(System.in);
//    sendMessage(scanner.nextLine());

    public BankClientMenu() {

        commandList = new ArrayList<String>();
        commandList.add("Set active client");
        commandList.add("Set active account");
        commandList.add("Withdraw");

    }

    public String execute() {
        System.out.print("Active client: ");
        if (activeClient==null) {
            System.out.println("not set");
        } else {
            System.out.println(activeClient.getName());
        }

        for (int i = 0; i < commandList.size(); i++) {
            System.out.println(i+ ") " + commandList.get(i));
        }
        String command = scanner.nextLine(); //TODO

        //TODO EXECUTE appropriate command

        //TODO 1, asking for client list

        return "";

    }
}
