package com.luxoft.CJP.April16.akrzos.bankapp.database.interfaces;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.ClientNotFoundException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;

import java.util.List;

/**
 * Created by akrzos on 2016-05-12.
 */
public interface ClientDAO {

    Client findClientByName (Bank bank, String name) throws DAOException, ClientNotFoundException;


    List<Client> getAllClients (Bank bank) throws DAOException;


    void save (Client client, Bank bank) throws DAOException;

    void remove (Client client) throws DAOException;
}
