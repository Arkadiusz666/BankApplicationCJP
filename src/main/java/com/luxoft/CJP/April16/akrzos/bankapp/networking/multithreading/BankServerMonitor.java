package com.luxoft.CJP.April16.akrzos.bankapp.networking.multithreading;

/**
 * Created by akrzos on 2016-05-09.
 */
public class BankServerMonitor implements Runnable {
    int queuedClients;

    @Override
    public void run() {
        while (true) {
            System.out.println("Clients in queue: " + queuedClients);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
