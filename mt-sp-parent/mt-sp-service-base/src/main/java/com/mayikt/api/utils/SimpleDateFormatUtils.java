package com.mayikt.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化日期的工具类
 *
 * @author
 */
public class SimpleDateFormatUtils {
    /**
     * yyyy-MM-dd HH:mm:ss 类型的日期 时间格式
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd类型的日期格式
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 根据输入的format格式，以及format字符串，返回对应的日期
     *
     * @param pattern，字符串的format格式，例如：yyyy-MM-dd        HH:mm:ss
     * @param dateFormatStr，format后的日期字符串，例如：2015-02-10 22:00:00
     * @return java.util.Date对象
     * @throws ParseException
     */
    public static Date getDataByFormatString(String pattern, String dateFormatStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(dateFormatStr);
        return date;
    }

    /**
     * @param date,需要转换为指定格式的日期对象
     * @return
     */
    public static String getFormatStrByPatternAndDate( Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        String formatStr = simpleDateFormat.format(date);
        return formatStr;
    }

}