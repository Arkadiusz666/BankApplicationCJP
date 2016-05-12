package com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.BankNotFoundException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;

/**
 * Created by akrzos on 2016-05-12.
 */
public interface BankDAO {


    Bank getBankByName (String name) throws DAOException, BankNotFoundException;

    void save(Bank bank) throws DAOException;

    void remove(Bank bank) throws DAOException;
}
