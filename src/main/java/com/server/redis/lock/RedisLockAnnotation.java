package com.server.redis.lock;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLockAnnotation {

    int expireTime() default 1000;  //设置一秒

    String key() default "";

}
