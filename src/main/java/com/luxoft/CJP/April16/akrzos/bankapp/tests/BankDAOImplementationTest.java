package com.luxoft.CJP.April16.akrzos.bankapp.tests;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.SavingAccount;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Gender;
import com.luxoft.CJP.April16.akrzos.bankapp.database.BankDAOImplementation;
import com.luxoft.CJP.April16.akrzos.bankapp.database.DBInitializer;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.BankNotFoundException;
import com.luxoft.CJP.April16.akrzos.bankapp.database.dbexceptions.DAOException;
import com.luxoft.CJP.April16.akrzos.bankapp.helpers.Helper;
import com.luxoft.CJP.April16.akrzos.bankapp.reflection.TestService;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by akrzos on 2016-05-30.
 */
public class BankDAOImplementationTest {
    Bank bank1,bank2;
    @Before
    public void initializeBanksForTesting() {
        bank1 = Helper.generateBank();
        bank2 = Helper.generateBank();
    }

    @Test
    public void testInsert() {
        BankDAOImplementation bankDAOImplementation = new BankDAOImplementation();
        DBInitializer initializer = new DBInitializer();
        initializer.deinitialize();
        initializer.initialize();
        try {
            bankDAOImplementation.saveWithContent(bank1);
            bank1=bankDAOImplementation.getBankByNameWithContent("UBS");
        } catch (DAOException | BankNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue (TestService.isEqualsExceptForFieldsMarkedasNoDB (bank1, bank2));
    }

    @Test
    //TODO to be reviewed
    public void testUpdate() throws Exception {
        BankServiceImplementation service = new BankServiceImplementation();
        Client client = new Client(Gender.MALE, "Jozek Sztacheta z poznania", "Poznan");
        service.addAccount(bank1,client,new SavingAccount(666));
        bank1.addClient(client);
        BankDAOImplementation bankDAOImplementation = new BankDAOImplementation();
        bankDAOImplementation.saveWithContent(bank1);
        Bank bankLoaded = bankDAOImplementation.getBankByNameWithContent("UBS");
        assertTrue (TestService.isEqualsExceptForFieldsMarkedasNoDB (bank1, bankLoaded));


    }

}