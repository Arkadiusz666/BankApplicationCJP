package com.luxoft.CJP.April16.akrzos.bankapp.database;

import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.BankNotFoundException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by akrzos on 2016-05-17.
 */
public class DBInitializer {


    public void initialize() {



        String sql = "SELECT ID, NAME FROM BANK WHERE name=?";
        PreparedStatement stmt;
//        try {
//            openConnection();
//            stmt = conn.prepareStatement(sql);
//            stmt.setString(1, name);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                int id  = rs.getInt("ID");
//                bank.setBankId(id); //TODO
//            } else {
//                throw new BankNotFoundException();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new DAOException();
//        } finally {
//            closeConnection();
//        }
//        return bank;


    }
}
