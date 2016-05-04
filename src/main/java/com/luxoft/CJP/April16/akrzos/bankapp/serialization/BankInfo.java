package com.luxoft.CJP.April16.akrzos.bankapp.serialization;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;

import java.io.Serializable;

/**
 * Created by akrzos on 2016-05-04.
 */
public class BankInfo implements Serializable {

    private static final long serialVersionUID = -434224943404106597L;
    private String bankInfo;
    private Bank bank;

    public BankInfo(Bank bank) {
        this.bank = bank;
        this.bankInfo = bank.toString();
    }

    public String getBankInfo() {
        return bankInfo;
    }
}
