package com.luxoft.CJP.April16.akrzos.bankapp.database;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces.ClientDAO;

import java.util.List;

/**
 * Created by akrzos on 2016-05-12.
 */
public class ClientDAOImplementation extends BaseDAOImplementation implements ClientDAO {
    @Override
    public Client findClientByName(Bank bank, String name) throws DAOException {
        return null;
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
