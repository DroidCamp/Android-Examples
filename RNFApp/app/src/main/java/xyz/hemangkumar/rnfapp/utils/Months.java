package xyz.hemangkumar.rnfapp.utils;

import java.util.HashMap;

/**
 * Created by Hemang on 14/09/16.
 */
public class Months {
    static HashMap<String, String> months;

    public static String getMonthFromDate(String date){
        months = new HashMap();

        months.put("1", "January");
        months.put("2", "February");
        months.put("3", "March");
        months.put("4", "April");
        months.put("5", "May");
        months.put("6", "June");
        months.put("7", "July");
        months.put("8", "August");
        months.put("9", "September");
        months.put("10", "October");
        months.put("11", "November");
        months.put("12", "December");

        return months.get(date);
    }
}
