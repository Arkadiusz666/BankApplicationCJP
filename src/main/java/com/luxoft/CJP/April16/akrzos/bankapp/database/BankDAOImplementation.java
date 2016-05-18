package com.luxoft.CJP.April16.akrzos.bankapp.database;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.BankNotFoundException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces.AccountDAO;
import com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces.BankDAO;
import com.luxoft.CJP.April16.akrzos.bankapp.helpers.Helper;

import java.sql.*;

/**
 * Created by akrzos on 2016-05-12.
 */
public class BankDAOImplementation extends BaseDAOImplementation implements BankDAO {

    public Bank getBankByName(String name) throws DAOException, BankNotFoundException {
        Bank bank = new Bank(name);
        String sql = "SELECT ID, NAME FROM BANK WHERE NAME=?";
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

    public void save(Bank bank) throws DAOException {
        //TODO cascade users as well
        String sql = "INSERT INTO BANK (NAME) VALUES(?)";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bank.getName());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
        }
        closeConnection();
    }

    public void remove(Bank bank) throws DAOException {
        String sql = "DELETE FROM BANK WHERE NAME=?";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bank.getName());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
        }
        closeConnection();

    }

    public static void main(String[] args) {
        BankDAOImplementation bankdao = new BankDAOImplementation();
        Bank bank = Helper.generateBank();

        BankDAO bankDAO = new BankDAOImplementation();
        AccountDAO accountDAO = new AccountDAOImplementation();

        try {
            bankDAO.save(bank);
            for (Client client : bank.getClients()) {
                for (Account account : client.getAccounts()) {
                    accountDAO.save(account, client);
                }
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }



//        DBInitializer initializer = new DBInitializer();
//        initializer.deinitialize();
//        initializer.initialize();


//        try {
////            bankdao.getBankByName("UBS");
//            bankdao.save(bank);
//            bankdao.save(bank);
////            bankdao.getBankByName("UBS");
//            bankdao.remove(bank);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
    }

}
