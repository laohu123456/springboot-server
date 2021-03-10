package com.server.config;

import com.server.redis.servcie.RedisStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

    @Autowired
    private RedisStringService redisStringService;

    public boolean equalUser_id(String currentUserId, String createUserId) {
        return createUserId.equals(currentUserId);
    }

}
