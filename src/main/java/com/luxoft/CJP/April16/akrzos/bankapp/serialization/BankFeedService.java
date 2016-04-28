package com.luxoft.CJP.April16.akrzos.bankapp.serialization;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by akrzos on 2016-04-27.
 */
public class BankFeedService {
    Bank activeBank;

    public BankFeedService(Bank activeBank) {//TODO check if this is enough
        this.activeBank = activeBank;
    }

    public void loadFeed(String folder) throws IOException {
        //TODO
        Map<String,String> map = new TreeMap<String, String>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        String loadedFeed = ""; //accounttype=c;balance=100;overdraft=50;name=John;gender=f;

        fis = new FileInputStream(folder); //TODO this will get only one file - change to wildcard
        ois = new ObjectInputStream(fis);
        loadedFeed=ois.readUTF();

        for (String s : loadedFeed.split(";")) { //loads entries separated by ;
            String[] temp = s.split("=");
            map.put(temp[0],temp[1]);
        }

        activeBank.parseFeed(map);
    }
}
