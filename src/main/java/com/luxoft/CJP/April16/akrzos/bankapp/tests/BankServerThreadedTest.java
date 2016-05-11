package com.luxoft.CJP.April16.akrzos.bankapp.tests;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.networking.BankClient;
import com.luxoft.CJP.April16.akrzos.bankapp.networking.multithreading.BankClientBot;
import com.luxoft.CJP.April16.akrzos.bankapp.networking.multithreading.ServerThread;
import com.luxoft.CJP.April16.akrzos.bankapp.serialization.BankFeedService;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * Created by arkad_000 on 2016-05-10.
 */
public class BankServerThreadedTest {
    ExecutorService pool = Executors.newFixedThreadPool(50);
    @Test
    public void testConnections() throws Exception {
        Thread.sleep(100);
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1);

            pool.execute(new BankClientBot());
            Thread.sleep(1);

        }
        Thread.sleep(1000);


//        for (int i = 0; i < 100 ; i++) {
////            client.run();//TODO test
//        }

    }


}