package com.luxoft.CJP.April16.akrzos.bankapp.networking;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.exceptions.OverdraftLimitExceededException;

import java.io.IOException;

/**
 * Created by arkad_000 on 2016-05-02.
 */
public class BankServerGetResponse {
    private Bank bank;
    private String message;

    public BankServerGetResponse(Bank bank) {
        this.bank = bank;
    }

    public String getResponse(String command) {
        message="";
        //GETCLIENTSLIST|nameregex
        if (command.matches("GETCLIENTSLIST.+")) {
//            String[] searchStringlist = command.split("\\|");
//            for (int i = 0; i < searchStringlist.length; i++) {
//                System.out.println(i+ " "+searchStringlist[i]);
//            }
            String searchString = command.split("\\|")[1];
            System.out.println("Search string: " + searchString);//TEST
            for (String s : bank.getClientsByName().keySet()) {
                if (s.matches(".*"+searchString+".*")) {
                    message+=s+"|";
                }
            }
            if (message.length()>0) {
                message=message.substring(0,message.length()-1);
            }
            return "RESPONSE"+message;
        }

        if (command.matches("TEST"))
        //TODO just for testing the connection
        if (command.matches("TEST")) {
            return "TEST";
        }
        //GET CLIENTSTAT|name
        if (command.matches("CLIENTSTAT.+")) {
            System.out.println("CLIENTSTAT"); //TODO testline
            String name = command.split("\\|")[1];
            return bank.getClientsByName().get(name).toString();
            //TODO - more tidy way to return this
        }
        //WITHDRAW|name|amount|account???
        if (command.matches("WITHDRAW.+")) {
            String name = command.split("\\|")[1];
            float amount = Float.parseFloat(command.split("\\|")[2]);
            //TODO - which account to serve?
            try {
                bank.getClientsByName().get(name).getActiveAccount().withdraw(amount);
                return amount +" succesfully withdrawn";
            } catch (OverdraftLimitExceededException e) {
                e.printStackTrace();
            }
            return "Error while withdrawing";
        }

        return "nic";
    }
}
