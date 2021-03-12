package com.server.annotation;

import com.server.redis.servcie.RedisSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = Ordered.LOWEST_PRECEDENCE - 1 )
public class findPath implements ApplicationRunner {

    @Autowired
    private RedisSetService redisSetService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        redisSetService.insertZset();
    }
}
