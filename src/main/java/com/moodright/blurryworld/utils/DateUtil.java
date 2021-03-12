package com.moodright.blurryworld.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具类
 * @author moodright
 * @date 2021/3/12
 */

public class DateUtil {
    /**
     * 将生日字符串转换为 java.util.Date 对象
     * @param dateString 时间字符串
     * @return java.util.Date
     */
    public static Date birthdayStringToDate(String dateString) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = formatter.parse(dateString);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
