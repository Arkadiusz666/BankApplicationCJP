package com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces;

import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;

import java.sql.Connection;

/**
 * Created by akrzos on 2016-05-12.
 */
public interface BaseDAO {
    Connection openConnection() throws DAOException;
    void closeConnection();
}
