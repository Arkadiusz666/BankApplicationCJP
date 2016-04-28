package com.luxoft.CJP.April16.akrzos.bankapp.tests;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.serialization.BankFeedService;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by arkad_000 on 2016-04-28.
 */
public class BankFeedServiceTest {

    @Test
    public void testLoadFeed() throws Exception {
        Bank bank = new Bank("Bank");
        BankFeedService feedService = new BankFeedService(bank);
        feedService.loadFeed("feeds");
        bank.printReport();
    }
}