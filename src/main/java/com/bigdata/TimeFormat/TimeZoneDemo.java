package com.bigdata.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.SimpleFormatter;

public class TimeZoneDemo {

    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//设置日期格式
        String date = df.format(new Date());
        System.out.println(date);

        //本地时区
        SimpleDateFormat dateFormat = new SimpleDateFormat("Z");
        String localTimeZone = dateFormat.format(new Date());
        TimeZone local = TimeZone.getTimeZone(localTimeZone);

        System.out.println(myTimeZone("GMT" + localTimeZone, System.currentTimeMillis()));
    }

    /**
     * @param timeZone 需要格式化的 时区  GMT
     * @param dateTime
     * @return
     */
    public static String myTimeZone(String timeZone, Long dateTime) {

        TimeZone tz = TimeZone.getTimeZone(timeZone);
        TimeZone.setDefault(tz);   //仅在当前线程有效，默认为系统的时区
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return df.format(dateTime);
    }
}
