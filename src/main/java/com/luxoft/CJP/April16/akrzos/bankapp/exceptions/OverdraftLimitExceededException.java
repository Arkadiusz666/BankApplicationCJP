package com.luxoft.CJP.April16.akrzos.bankapp.exceptions;

import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;
import com.luxoft.CJP.April16.akrzos.bankapp.accounts.CheckingAccount;

/**
 * Created by akrzos on 2016-04-13.
 */
public class OverdraftLimitExceededException extends NotEnoughFundsException {
    private CheckingAccount account;
    private float amount;

    public OverdraftLimitExceededException(float amount, CheckingAccount account) {
        super(amount, account);
    }

    @Override
    public String getMessage() {
        return("Not enough founds to withdraw");
        //TODO
        //return ("Not enough founds to withdraw "+ amount +". Maximum account to withdraw: " + account.getBalance()+account.getOverdraft());
    }
}



