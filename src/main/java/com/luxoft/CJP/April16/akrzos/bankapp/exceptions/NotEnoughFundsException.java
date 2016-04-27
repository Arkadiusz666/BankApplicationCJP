package com.luxoft.CJP.April16.akrzos.bankapp.exceptions;

import com.luxoft.CJP.April16.akrzos.bankapp.accounts.Account;

/**
 * Created by akrzos on 2016-04-13.
 */
public class NotEnoughFundsException extends BankException {
    private float amount;
    private Account account;
    NotEnoughFundsException(float amount, Account account) {
        this.account=account;
        this.amount=amount;
    }

    @Override
    public String getMessage() {

        return ("Not enough founds to withdraw "+ amount +". Current balance: " +account.getBalance());
    }
}


