package com.luxoft.CJP.April16.akrzos.bankapp.database;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.BankNotFoundException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.ClientNotFoundException;
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
        String sql = "SELECT *FROM BANKS WHERE BANKS_NAME=?";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id  = rs.getInt("BANKS_ID");
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
        //TODO UPDATE OR INSERT IF NEEDED
        String sql = "INSERT INTO BANKS (BANKS_NAME) VALUES(?)";
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
            closeConnection();
        }
        try {
            bank.setBankId(getBankByName(bank.getName()).getBankId());
        } catch (BankNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void remove(Bank bank) throws DAOException {
        String sql = "DELETE FROM BANKS WHERE BANKS_ID=?";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bank.getBankId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }
    }

    public static void main(String[] args) {
        BankDAOImplementation bankDAO = new BankDAOImplementation();
        AccountDAOImplementation accountDAO= new AccountDAOImplementation();
        ClientDAOImplementation clientDAO = new ClientDAOImplementation();

        Bank bank = Helper.generateBank();
        DBInitializer dbInitializer = new DBInitializer();
        dbInitializer.deinitialize();
        dbInitializer.initialize();
        dbInitializer.fill(bank);

        try {
            Client client1= clientDAO.findClientByName(bank, "Arek Krzos");
            clientDAO.remove(client1);
            accountDAO.removeByClientId(2);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }

//        try {
//            for (Account account : accountdao.getClientAccounts(0)) {
//                System.out.println(account);
//            }
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }


    }

}
