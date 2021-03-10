package com.server.utils;

import java.util.UUID;

public class OtherUtils {

    public static boolean equalLongValue(long value1, long value2) {
        Long val1 = new Long(value1);
        Long val2 = new Long(value2);
        return val1.longValue() == val2.longValue();
    }

    public static boolean equalLongValue(Long value1, Long value2) {
        Long val1 = new Long(value1);
        Long val2 = new Long(value2);
        return val1.longValue() == val2.longValue();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
