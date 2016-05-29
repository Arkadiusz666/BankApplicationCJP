package com.luxoft.CJP.April16.akrzos.bankapp.tests;

import com.luxoft.CJP.April16.akrzos.bankapp.database.annotation.NoDB;
import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by akrzos on 2016-05-25.
 */
public class ReflectionTest {
    /**
     * This method should analyze the fields o1 and o2.
     * It should compare all the fields with the help of equals,
     * except for those fields that are marked with an annotation
     *  @NoDB
     * And return true, if all the fields are equal.
     * Also it should be able to compare the collections.
     */
    @Test
    public static boolean isEqualsExceptForFieldsMarkedasNoDB (Object o1, Object o2) {
        Field[] fields1 = o1.getClass().getDeclaredFields();
        Field[] fields2 = o1.getClass().getDeclaredFields();

        ArrayList<Field> filteredFields1 = new ArrayList<>();
        ArrayList<Field> filteredFields2 = new ArrayList<>();

        for (Field field : fields1) {
            if (field.getAnnotation(NoDB.class) ==null) {
                filteredFields1.add(field);
            }
        }

        for (Field field : fields2) {
            if (field.getAnnotation(NoDB.class) ==null) {
                filteredFields2.add(field);
            }
        }

        return Objects.equals(filteredFields1,filteredFields2);

    }

}
