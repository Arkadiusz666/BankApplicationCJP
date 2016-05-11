package com.luxoft.CJP.April16.akrzos.bankapp.networking.multithreading;

import com.luxoft.CJP.April16.akrzos.bankapp.networking.BankClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by akrzos on 2016-05-11.
 */
public class BankClientBot extends BankClient {
    @Override
    public void run() {
        try {

            requestSocket = new Socket(SERVER, 2002);
            oos = new ObjectOutputStream(requestSocket.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(requestSocket.getInputStream());

            activeClient="Arek Krzos";
            withdrawFromActiveClient(1);
            closeConnection();

        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            // 4: Closing connection
            try {
                ois.close();
                oos.close();
                requestSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public void closeConnection() {
        sendMessage("EXIT");
    }
}
