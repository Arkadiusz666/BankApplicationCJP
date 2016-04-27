package com.luxoft.CJP.April16.akrzos.bankapp.listeners;

import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;

/**
 * Created by akrzos on 2016-04-14.
 */
public class EmailNotificationListener implements ClientRegistrationListener {
    @Override
    public void onClientAdded(Client client) {
        System.out.println("Notification email for client "+client.getName()+" to be sent");
    }
}


