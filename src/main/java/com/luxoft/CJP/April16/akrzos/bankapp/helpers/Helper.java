package com.luxoft.CJP.April16.akrzos.bankapp.helpers;

import java.util.Scanner;

/**
 * Created by arkad_000 on 2016-05-02.
 */
public class Helper {
    public static void pressAnyKeyToContinue() {
        System.out.println("Press Enter to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
    public static String splitAndChoose (String delimitedString, String toBeIgnored) {
        String message;
        Scanner scanner = new Scanner(System.in);
        message = delimitedString.replaceAll(toBeIgnored, "");
        String[] messageList = message.split("\\|");
        System.out.println("Choose one:");
        if (messageList.length<1 || messageList[0].equals("")) {
            return "No item found";
        } else {
            for (int i = 0; i < messageList.length; i++) {
                System.out.println(i+") " + messageList[i]);
            }
            while (true) {
                int choice = scanner.nextInt(); //TODO
                if (choice<0||choice>=messageList.length) {
                    System.out.println("Incorrect input");
                } else {
                    return messageList[choice];
                }
            }
        }
    }
}
