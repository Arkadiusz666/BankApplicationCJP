package com.luxoft.CJP.April16.akrzos.bankapp.accounts;

import com.luxoft.CJP.April16.akrzos.bankapp.Report;
import com.luxoft.CJP.April16.akrzos.bankapp.client.Client;
import com.luxoft.CJP.April16.akrzos.bankapp.exceptions.OverdraftLimitExceededException;

/**
 * Created by akrzos on 2016-04-12.
 */
public interface Account extends Report {
    public float getBalance();
    public void deposit(float amount);
    public void withdraw(float amount) throws OverdraftLimitExceededException;
    public void decimalValue();
    public String getAccountType();
    public float getOverdraft();
}


