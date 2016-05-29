package com.luxoft.CJP.April16.akrzos.bankapp.reflection;

import com.luxoft.CJP.April16.akrzos.bankapp.database.annotation.NoDB;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by arkad_000 on 2016-05-29.
 */
public class TestService {
    public static boolean isEqualsExceptForFieldsMarkedasNoDB (Object o1, Object o2) {
        Field[] fields1 = o1.getClass().getDeclaredFields();

        Field[] fields2 = o1.getClass().getDeclaredFields();

        ArrayList<Field> filteredFields1 = new ArrayList<>();
        ArrayList<Field> filteredFields2 = new ArrayList<>();

        for (Field field : fields1) {
            if (field.getAnnotation(NoDB.class) ==null) {
                field.setAccessible(true);
                filteredFields1.add(field);
            }
        }

        for (Field field : fields2) {
            if (field.getAnnotation(NoDB.class) ==null) {
                field.setAccessible(true);
                filteredFields2.add(field);
            }
        }
        return Objects.equals(filteredFields1,filteredFields2);

    }
}
