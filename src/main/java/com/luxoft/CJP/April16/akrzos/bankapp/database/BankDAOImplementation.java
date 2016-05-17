package com.luxoft.CJP.April16.akrzos.bankapp.database;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.BankNotFoundException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces.BankDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String bankName = bank.getName();
        String sql = "INSERT INTO BANK VALUES (NULL, ?)";
        PreparedStatement stmt;
            openConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bankName);
            ResultSet rs = stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }




    }

    public void remove(Bank bank) throws DAOException {

    }

    public static void main(String[] args) {
        BankDAOImplementation bankdao = new BankDAOImplementation();
        Bank bank = new Bank("Moj nowy bank kurde");
        try {
            bankdao.save(bank);
            bankdao.getBankByName("UBS");
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (BankNotFoundException e) {
            e.printStackTrace();
        }
    }

}
