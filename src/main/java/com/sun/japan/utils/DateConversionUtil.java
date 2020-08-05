package com.sun.japan.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversionUtil {
    public static Date strToDate(String str){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String strToPeriod(String str){
        String[] strs = str.split("-");
        str = strs[0] + strs[1] + strs[2];
        return str;
    }

    public static String dateToTime(Date date){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }
}
