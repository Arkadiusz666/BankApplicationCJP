package com.luxoft.CJP.April16.akrzos.bankapp.client;

/**
 * Created by akrzos on 2016-04-12.
 */
public enum Gender {

    MALE("Mr.") {
        public void work() {
        }
    }, FEMALE("Mrs.") {
        public void work() {
        }
    };


    private String salutation;

    private Gender(String salutation) {
        this.salutation = salutation;
    }

    public String getSalutation() {
        return salutation;
    }

}


