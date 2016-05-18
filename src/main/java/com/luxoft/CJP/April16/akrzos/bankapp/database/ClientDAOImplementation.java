package com.luxoft.CJP.April16.akrzos.bankapp.database;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.BankNotFoundException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces.ClientDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by akrzos on 2016-05-12.
 */
public class ClientDAOImplementation extends BaseDAOImplementation implements ClientDAO {
    @Override
    public Client findClientByName(Bank bank, String name) throws DAOException, ClientNotFoundException {
        Client client = new Client();
        client.setName(name);
        String sql = "SELECT ID, NAME FROM CLIENTS WHERE NAME=?";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id  = rs.getInt("ID");
                client.setClientId(id); //TODO
                client.setName(rs.getNString("CLIENTS_NAME"));
                client.setGender(rs.getNString("CLIENTS_GENDER")); //TODO test gender setter
                client.setCity(rs.getNString("CLIENTS_CITY"));
                //TODO BANK FIND AND SET

            } else {
                throw new ClientNotFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }
        return client;
    }

    @Override
    public List<Client> getAllClients(Bank bank) throws DAOException {
        return null;
    }

    @Override
    public void save(Client client) throws DAOException {

    }

    @Override
    public void remove(Client client) throws DAOException {

    }
}
