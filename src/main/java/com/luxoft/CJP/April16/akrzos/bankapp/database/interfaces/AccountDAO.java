package com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces;

import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;

import java.util.List;

/**
 * Created by akrzos on 2016-05-12.
 */
public interface AccountDAO {
    public void save(Account account) throws DAOException;
    public void add(Account account) throws DAOException;
    public void removeByClientId(int idClient) throws DAOException;
    public List<Account> getClientAccounts(int idClient) throws DAOException;
}
