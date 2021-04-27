package com.server.redis.lock;

import com.server.utils.OtherUtils;

public class GetRedisRequestId {

    private static ThreadLocal<String> REQUEST_ID = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return OtherUtils.getUUID();
        }
    };

    public static String getRequestId(){
        return REQUEST_ID.get();
    }

    public static void remove(){
        REQUEST_ID.remove();
    }

}
