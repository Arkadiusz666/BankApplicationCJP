package com.luxoft.CJP.April16.akrzos.bankapp.database;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Gender;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.ClientNotFoundException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces.AccountDAO;
import com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces.ClientDAO;
import com.luxoft.CJP.April16.akrzos.bankapp.helpers.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akrzos on 2016-05-12.
 */
public class ClientDAOImplementation extends BaseDAOImplementation implements ClientDAO {
    @Override
    public Client findClientByName(Bank bank, String name) throws DAOException, ClientNotFoundException {
        Client client = new Client();
        client.setName(name);
        String sql = "SELECT * FROM CLIENTS WHERE CLIENTS_NAME=?";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id  = rs.getInt("CLIENTS_ID");
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
        List<Client> clients = new ArrayList<>();

        String sql = "SELECT * FROM CLIENTS WHERE CLIENTS_BANK_ID=" +bank.getBankId();
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setClientId(rs.getInt("CLIENTS_ID"));
                client.setName(rs.getString("CLIENTS_NAME"));
                if (rs.getString("CLIENTS_GENDER").equals("M")) {
                    client.setGender(Gender.MALE);
                } else {
                    client.setGender(Gender.FEMALE);
                }
                client.setCity(rs.getString("CLIENTS_CITY"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return clients;
    }

    @Override
    public void save(Client client, Bank bank) throws DAOException {
        //TODO UPDATE OR INSERT - BASE ON ID
        String sql = "INSERT INTO CLIENTS (CLIENTS_NAME, CLIENTS_GENDER, CLIENTS_CITY, CLIENTS_BANKS_ID) \n" +
                "\tVALUES (?,?,?,?);";
        PreparedStatement stmt;
        try {
            openConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, client.getName());
            if (client.getGender().equals(Gender.MALE)) {
                stmt.setString(2, "M");
            } else {
                stmt.setString(2, "F");
            }
            stmt.setString(3, client.getCity());
            stmt.setInt(4, bank.getBankId());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }
        try {
            client.setClientId(findClientByName(bank, client.getName()).getClientId());
        } catch (ClientNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Client client) throws DAOException {
        DBInitializer initilizer = new DBInitializer();
        String sql = "DELETE FROM CLIENTS WHERE CLIENTS_ID=" + client.getClientId();
        //TODO - check if this should be done by name or by id?
        initilizer.sqlQueryExecutor(sql);
        AccountDAOImplementation accountDAO = new AccountDAOImplementation();
        accountDAO.removeByClientId(client.getClientId());
    }
}
