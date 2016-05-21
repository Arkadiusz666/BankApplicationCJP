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
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akrzos on 2016-05-12.
 */
public class BankDAOImplementation extends BaseDAOImplementation implements BankDAO {

    public Bank getBankByName(String name) throws DAOException, BankNotFoundException {
        Bank bank = new Bank(name);
        String sql = "SELECT * FROM BANKS WHERE BANKS_NAME=?";
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
    //TODO TEST IT!!!!!!!!!!!!
    public Bank getBankByNameWithContent(String name) throws BankNotFoundException, DAOException {
        Bank bank = getBankByName(name);
        ClientDAOImplementation clientDAO = new ClientDAOImplementation();
        AccountDAOImplementation accountDAO = new AccountDAOImplementation();
        BankServiceImplementation service = new BankServiceImplementation();

        for (Client client : clientDAO.getAllClients(bank)) {
            service.addClient(bank, client);
            for (Account account : accountDAO.getClientAccounts(client.getClientId())) {
                service.addAccount(bank,client,account);
            }
        }

        return bank;
    }

    public ArrayList<Bank> getBanksList() {
        ArrayList<Bank> bankList = new ArrayList<>();

        String sql = "SELECT * FROM BANKS";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Bank bank = new Bank(rs.getString("BANKS_NAME"));
                bank.setBankId(rs.getInt("BANKS_ID"));
                bankList.add(bank);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return bankList;

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

}
