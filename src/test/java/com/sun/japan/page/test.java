package com.sun.japan.page;


import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;

public class test {
    public static void main(String[] args) {
//        System.out.println(new Date());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String str = "2020-07-01";
//        try {
//            Date date = simpleDateFormat.parse(str);
//            System.out.println(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Date date = new Date();
//        System.out.println(date);
//        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
//        String dateStr = dateFormat.format(date);
//        System.out.println(dateStr);

//        Long time = new Date().getTime();
//        String dateNum = "lesson"+time.toString();
//        System.out.println(dateNum);
//        Date date = new Date();
//        String str = "<tr class='"+date+"'>" +
//                "                        <td><div class='checkbox'>" +
//                "                            <label>" +
//                "                                <input type='checkbox' class='lessonCheckbox' id='"+date+"' value='option1' aria-label='Checkbox without label text'/>" +
//                "                            </label>" +
//                "                        </div></td>" +
//                "                        <td><input type='text'/>日期</td>" +
//                "                        <td><input type='text'/>时间</td>" +
//                "                        <td><input type='text'/>内容</td>" +
//                "                    </tr>";
//        System.out.println(str);
        String str = "123456789";
        System.out.println(str.length());
        System.out.println(str.substring(1, str.length() - 1));
    }

}
