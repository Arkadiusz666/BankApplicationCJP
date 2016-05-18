package com.luxoft.CJP.April16.akrzos.bankapp.database;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.BankNotFoundException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces.AccountDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akrzos on 2016-05-12.
 */
public class AccountDAOImplementation  extends BaseDAOImplementation implements AccountDAO {
    @Override
    public void save(Account account, Client client) throws DAOException {
        String sql = "INSERT INTO ACCOUNTS (ACCOUNTS_CLIENT_ID, ACCOUNTS_TYPE, ACCOUNTS_BALANCE, ACCOUNTS_OVERDRAFT) \n" +
                "\tVALUES (?,?,?,?);";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, client.getClientId()); //TODO get client id
            //TODO - write a methot get owner to retrieve the client from account
            stmt.setString(2, account.getAccountType());
            stmt.setFloat(3, account.getBalance());
            stmt.setFloat(4, account.getOverdraft());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
        }
        closeConnection();
    }

    @Override
    public void add(Account account) throws DAOException {

    }

    @Override
    public void removeByClientId(int idClient) throws DAOException {
        DBInitializer initializer = new DBInitializer();
        String sql = "DELETE FROM CLIENTS WHERE CLIENT_ID='" + idClient +"';";
        initializer.sqlQueryExecutor(sql);
    }

    @Override
    public List<Account> getClientAccounts(int idClient) throws DAOException {
        List<Account> accounts = new ArrayList<>();

        Bank bank = new Bank(name);
        String sql = "SELECT ACCOUNTS_ID,ACCOUNTS_TYPE, ACCOUNTS_BALANCE, ACCOUNTS_OVERDRAFT FROM ACCOUNTS WHERE ACCOUNTS_CLIENT_ID=?";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id  = rs.getInt("ID");
                bank.setBankId(id); //TODO
            } else {
                throw new BankNotFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }
        return bank;
    }
}
