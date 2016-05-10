package com.luxoft.CJP.April16.akrzos.bankapp.networking.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by akrzos on 2016-05-09.
 */
public class BankServerMonitor implements Runnable {
    private AtomicInteger queuedClients;

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
    synchronized public void incrementCounter() { //TODO - check if synch is needed here
        queuedClients.incrementAndGet();
    }
    synchronized public void decrementCounter() { //TODO - check if synch is needed here
        queuedClients.decrementAndGet();
    }
}
