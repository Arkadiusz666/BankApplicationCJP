package com.luxoft.CJP.April16.akrzos.bankapp.networking;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.exceptions.OverdraftLimitExceededException;
import com.luxoft.CJP.April16.akrzos.bankapp.serialization.BankInfo;

import java.io.IOException;

/**
 * Created by arkad_000 on 2016-05-02.
 */
public class BankServerGetResponse {
    private Bank bank;
    private String message;

    public  BankServerGetResponse(Bank bank) {
        this.bank = bank;
    }

    public synchronized String getResponse(String command) {
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
                if (bank.getClientsByName().get(name).getActiveAccount()==null) {
                    return "No active account set (most likely client has no accounts)";
                } else {
                    bank.getClientsByName().get(name).getActiveAccount().withdraw(amount);
                    return amount +" succesfully withdrawn";
                }
            } catch (OverdraftLimitExceededException e) {
                e.printStackTrace();
            }
            return "Error while withdrawing";
        }
        //GETACCOUNTSLIST|name
        if ((command.matches("GETACCOUNTSLIST.+"))) {
            String name = command.split("\\|")[1];
            String accountsMessage = "ACCOUNTSLIST";

            for (Account account : bank.getClientsByName().get(name).getAccounts()) {
                accountsMessage+="|"+account.toString();
            }
            return accountsMessage;
        }
        //SETACTIVEACCOUNT|name|account (to string)
        if (command.matches("SETACTIVEACCOUNT.+")) {
            String name = command.split("\\|")[1];
            String accountName = command.split("\\|")[2];
            for (Account account : bank.getClientsByName().get(name).getAccounts()) {
                if (account.toString().equals(accountName)) {
                    bank.getClientsByName().get(name).setActiveAccount(account);
                }
            }
            return "Active account set: "+accountName;
        }
        //TODO REMOTE OFFICE COOMANDS
        //REMOTE_GETBANKINFO
        if (command.matches("REMOTE_GETBANKINFO.+")) {
            BankInfo bankInfo = new BankInfo(bank);

        }
        //

        return "nic";
    }
}
