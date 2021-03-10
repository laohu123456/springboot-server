package com.server.utils;

import com.server.constant.CommonConstant;

public class GetRedisKey {

    public static String getLoginJwtTime(String userid) {
        return CommonConstant.getLoginJwtTime() + userid;
    }

    public static String getLoginErrorSeconds(String userid) {
        return CommonConstant.getLoginErrorSeconds() + userid;
    }

}
