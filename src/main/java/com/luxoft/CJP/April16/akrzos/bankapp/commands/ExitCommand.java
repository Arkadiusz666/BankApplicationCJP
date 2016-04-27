package com.luxoft.CJP.April16.akrzos.bankapp.commands;

/**
 * Created by arkad_000 on 2016-04-25.
 */
public class ExitCommand implements Command {
    public void execute() {
        System.exit(0);
    }

    public void printCommandInfo() {
        System.out.println("Exit");
    }
}
