package com.luxoft.CJP.April16.akrzos.bankapp.networking.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by akrzos on 2016-05-09.
 */
public class BankServerMonitor implements Runnable {


    @Override
    public void run() {
        while (true) {

            System.out.println("Clients in queue: " + ServerThread.getConnectionsCounter());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            BankServerMonitor monitor = new BankServerMonitor();
            monitor.run();
        }
    }

}
