package com.luxoft.CJP.April16.akrzos.bankapp.tests;

import com.luxoft.CJP.April16.akrzos.bankapp.Bank;
import com.luxoft.CJP.April16.akrzos.bankapp.database.annotation.NoDB;
import com.luxoft.CJP.April16.akrzos.bankapp.helpers.Helper;
import com.luxoft.CJP.April16.akrzos.bankapp.reflection.TestService;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

/**
 * Created by akrzos on 2016-05-25.
 */
public class ReflectionTest {
    Bank bank1,bank2;
    @Before
    public void initializeBanksForTesting() {
        bank1 = Helper.generateBank();
        bank2 = Helper.generateBank();
    }

    @org.junit.Test
    public void testEquals() {
        assertTrue(TestService.isEqualsExceptForFieldsMarkedasNoDB(bank1, bank2));
    }

    /**
     * This method should analyze the fields o1 and o2.
     * It should compare all the fields with the help of equals,
     * except for those fields that are marked with an annotation
     *  @NoDB
     * And return true, if all the fields are equal.
     * Also it should be able to compare the collections.
     */

}
