package com.luxoft.CJP.April16.akrzos.bankapp.database;

import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces.AccountDAO;

import java.util.List;

/**
 * Created by akrzos on 2016-05-12.
 */
public class AccountDAOImplementation  extends BaseDAOImplementation implements AccountDAO {
    @Override
    public void save(Account account) throws DAOException {

    }

    @Override
    public void add(Account account) throws DAOException {

    }

    @Override
    public void removeByClientId(int idClient) throws DAOException {

    }

    @Override
    public List<Account> getClientAccounts(int idClient) throws DAOException {
        return null;
    }
}
