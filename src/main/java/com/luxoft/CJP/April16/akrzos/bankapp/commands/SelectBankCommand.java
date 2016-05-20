package com.luxoft.CJP.April16.akrzos.bankapp.commands;
import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.database.BankDAOImplementation;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.BankNotFoundException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
import com.luxoft.CJP.April16.akrzos.bankapp.helpers.Helper;
import java.util.ArrayList;

/**
 * Created by akrzos on 2016-05-20.
 */
public class SelectBankCommand implements Command {

    @Override
    public void execute() {
        printCommandInfo();
        BankDAOImplementation bankDAO = new BankDAOImplementation();
        HelperCommand helper = new HelperCommand();
        ArrayList<Bank> bankList = bankDAO.getBanksList();
        if (bankList.size()==0) {
            System.out.println("No banks to choose from!");
        }
        Bank bank =helper.chooseBank(bankList);
        try {
            bank = bankDAO.getBankByNameWithContent(bank.getName());
        } catch (BankNotFoundException | DAOException e) {
            e.printStackTrace();
        }
        BankCommander.currentBank=bank;
        System.out.println("Active bank set to " + bank.getName());
        Helper.pressAnyKeyToContinue();
    }

    public void printCommandInfo() {
        System.out.println("Choose active bank");
    }
}
