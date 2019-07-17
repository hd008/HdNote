package com.hd.hdnote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {
    public static void main(String[] args) {


        String date = "2019-07-1";
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //加上时间
        //必须捕获异常
        try {//判断日期大小
            Date fdate = sDateFormat.parse(date);
            Date odate = new Date();
            Calendar aCalendar = Calendar.getInstance();
            aCalendar.setTime(fdate);
            int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
            aCalendar.setTime(odate);
            int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
            int days = day2 - day1;
            System.out.print(days);


        } catch (ParseException px) {
            px.printStackTrace();
        }
    }
}
