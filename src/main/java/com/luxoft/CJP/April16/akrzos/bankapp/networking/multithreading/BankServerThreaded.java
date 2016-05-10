package com.luxoft.CJP.April16.akrzos.bankapp.networking.multithreading;

import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.SocketHandler;

/**
 * Created by akrzos on 2016-05-09.
 */
public class BankServerThreaded {
    final int POOL_SIZE = 10;


//    ServerSocket serverSocket = new ServerSocket(PORT);
    ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);

    public static void main(String[] args) {
        while (true) {
        }
    }

}
