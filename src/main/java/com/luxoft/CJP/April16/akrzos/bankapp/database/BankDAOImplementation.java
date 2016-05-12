package com.luxoft.CJP.April16.akrzos.bankapp.database;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.BankNotFoundException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces.BankDAO;
import com.sun.corba.se.pept.transport.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by akrzos on 2016-05-12.
 */
public class BankDAOImplementation extends BaseDAOImplementation implements BankDAO {

    @Override
    public Bank getBankByName(String name) throws DAOException, BankNotFoundException {
        Bank bank = new Bank(name);
        String sql = "SELECT ID, NAME FROM BANK WHERE name=?";
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

    @Override
    public void save(Bank bank) throws DAOException {

    }

    @Override
    public void remove(Bank bank) throws DAOException {

    }

    public static void main(String[] args) {
        BankDAOImplementation bankdao = new BankDAOImplementation();
        bankdao.getBankByName()
    }

}
