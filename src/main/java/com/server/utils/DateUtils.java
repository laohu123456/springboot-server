package com.server.utils;

import com.mchange.v2.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentDate(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }

    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return simpleDateFormat.format(new Date());
    }

}
