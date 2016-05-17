package com.luxoft.CJP.April16.akrzos.bankapp.database;

import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces.BaseDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by akrzos on 2016-05-12.
 */
public class BaseDAOImplementation implements BaseDAO {
    Connection conn;
    public Connection openConnection() throws DAOException {
        try {
            Class.forName("org.h2.Driver"); // this is driver for H2
            conn = DriverManager.getConnection("jdbc:h2:~/ormTraining",
                    "sa", // login
                    "" // password
            );
            return conn;
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
