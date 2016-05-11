package com.luxoft.CJP.April16.akrzos.bankapp.networking.multithreading;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.networking.BankServerGetResponse;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by akrzos on 2016-05-09.
 */
public class ServerThread implements Runnable{
    private static AtomicInteger connectionsCounter = new AtomicInteger(0); //atomic?
    private  Socket connection;
    private  Bank bank;
    private  BankService bankService;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String message;

    public ServerThread(Socket connection, Bank bank, BankService bankService) {
        this.connection = connection;

        connectionsCounter.incrementAndGet();
        this.bank = bank;
        this.bankService = bankService;
    }

    public void run() {
        System.out.println("Connection received from "
                + connection.getInetAddress().getHostName());
        // 3. get Input and Output streams
        try {
            oos = new ObjectOutputStream(connection.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(connection.getInputStream());
            clientServerLoop();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                ois.close();
                oos.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connectionsCounter.decrementAndGet();
        }
    }

    void clientServerLoop() {
        BankServerGetResponse responder = new BankServerGetResponse(bank);
        do {
            try {
                try {
                    message = (String) ois.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(message);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String response = responder.getResponse(message);
            sendMessage(response);

        } while (!message.equals("EXIT"));
    }
    void sendMessage(final String msg) {
        try {
            oos.writeObject(msg);
            oos.flush();
            System.out.println("server>" + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
