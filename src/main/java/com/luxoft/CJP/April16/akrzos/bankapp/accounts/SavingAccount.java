package com.luxoft.CJP.April16.akrzos.bankapp.accounts;

import com.luxoft.CJP.April16.akrzos.bankapp.accounts.AbstractAccount;

/**
 * Created by akrzos on 2016-04-12.
 */
public class SavingAccount extends AbstractAccount {

    public SavingAccount(float balance) {
        super(balance);
        if(balance<0) throw new IllegalArgumentException();
//        accountId=idCounter;
//        idCounter++;
    }





}


