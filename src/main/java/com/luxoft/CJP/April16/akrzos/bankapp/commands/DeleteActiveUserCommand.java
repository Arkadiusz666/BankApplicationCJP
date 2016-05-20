package com.luxoft.CJP.April16.akrzos.bankapp.commands;

import com.luxoft.CJP.April16.akrzos.bankapp.database.ClientDAOImplementation;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces.ClientDAO;
import com.luxoft.CJP.April16.akrzos.bankapp.helpers.Helper;

/**
 * Created by akrzos on 2016-05-20.
 */
public class DeleteActiveUserCommand implements Command {
    @Override
    public void execute() {
        printCommandInfo();
        if (BankCommander.currentClient==null) {
            System.out.println("No client selected!");
            Helper.pressAnyKeyToContinue();
            return;
        }
        if (Helper.areYouSure()) {
            ClientDAOImplementation clientDAO = new ClientDAOImplementation();
            try {
                clientDAO.remove(BankCommander.currentClient);
                BankCommander.currentClient=null;
            } catch (DAOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void printCommandInfo() {
        System.out.println("Deletes active user from database");

    }
}
