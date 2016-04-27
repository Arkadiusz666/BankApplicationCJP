package com.luxoft.CJP.April16.akrzos.bankapp.commands;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.services.BankServiceImplementation;

/**
 * Created by arkad_000 on 2016-04-18.
 */
public interface Command {
    public void execute();
    public void printCommandInfo();
}

