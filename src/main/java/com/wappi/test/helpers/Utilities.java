package com.wappi.test.helpers;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utilities {

    private Utilities(){
    }

    public static String getCurrentDate(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public static String getRandomBirthdate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Faker faker = new Faker();
        return sdf.format(
            faker.date().birthday()
        );
    }

    public static Object getRandomValueIntoList(List<?> list) {
        Object object = new Object();
        if (!list.isEmpty()) {
            int index = selectRandomValue(0, list.size());
            object = list.get(index);
        } else {
            Report.reportFail("Looking for an element inside empty list");
        }
        return object;
    }

    public static int selectRandomValue(int min, int max) {
        return new Faker().number().numberBetween(min, max);
    }

}
