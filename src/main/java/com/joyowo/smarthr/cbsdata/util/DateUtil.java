package com.joyowo.smarthr.cbsdata.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Spark
 * @Create 2019/4/23
 */
public class DateUtil {

    public static final String formdatetime1 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 字符串转换日期，可采用DateUtil常量属性做参数
     *
     * @param str
     * @return
     */
    public static Date stringToDate(String str, String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        if (null != str && str.length() > 0) {
            try {
                return format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 日期转换成字符串，可采用DateUtil常量属性做参数
     *
     * @param date
     * @param type
     * @return
     */
    public static String dateToString(Date date, String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获取前n天时间
     *
     * @param n
     * @return
     */
    public static String getLastDay(int n) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -n);
        date = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
}
