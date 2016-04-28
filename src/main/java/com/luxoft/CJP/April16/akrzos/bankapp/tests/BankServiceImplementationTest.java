package com.luxoft.CJP.April16.akrzos.bankapp.tests;

import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;

import static org.junit.Assert.*;

/**
 * Created by arkad_000 on 2016-04-28.
 */
public class BankServiceImplementationTest {

    @org.junit.Test
    public void testSaveClient() throws Exception {
        Client testClient = new Client();
        testClient.setName("A1rekaa");
        BankServiceImplementation service = new BankServiceImplementation();
        service.saveClient(testClient);
    }

    @org.junit.Test
    public void testLoadClient() throws Exception {
        Client testClient = null;
        BankServiceImplementation service = new BankServiceImplementation();
        testClient= service.loadClient();

    }
}