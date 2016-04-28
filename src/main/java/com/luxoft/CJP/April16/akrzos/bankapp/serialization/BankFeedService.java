package com.luxoft.CJP.April16.akrzos.bankapp.serialization;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;

import java.io.*;
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
        File directory = new File(folder);
        File[] listOfFiles = directory.listFiles();
        for (File file : listOfFiles) {
            Map<String,String> map = new TreeMap<String, String>();

            FileInputStream fis = new FileInputStream(file);

            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String loadedLine = null;
            while ((loadedLine = br.readLine()) != null) {
                for (String s : loadedLine.split(";")) { //loads entries separated by ;
                    String[] temp = s.split("=");
                    map.put(temp[0],temp[1]);
                }
                activeBank.parseFeed(map);
            }

            br.close();





        }


        //TODO do it for all teh lines

    }
}
