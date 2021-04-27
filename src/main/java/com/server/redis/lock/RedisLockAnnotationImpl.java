package com.server.redis.lock;

import com.server.redis.jedisserver.config.JedisConfig;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;

@Component
@Aspect
public class RedisLockAnnotationImpl {

    @Autowired
    private JedisConfig jedisConfig;

    @Pointcut("@annotation(com.server.redis.lock.RedisLockAnnotation)")
    public void redisLockAnnotationImpl(){}

    @Before("redisLockAnnotationImpl()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RedisLockAnnotation annotation = method.getAnnotation(RedisLockAnnotation.class);
        int expireTime = annotation.expireTime();
        String key = annotation.key();

    }

    @After("redisLockAnnotationImpl()")
    public void after(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RedisLockAnnotation annotation = method.getAnnotation(RedisLockAnnotation.class);
        String key = annotation.key();
    }

    @AfterThrowing(pointcut = "redisLockAnnotationImpl()", throwing = "ex")
    public void afterThrowing(Throwable ex){

    }

    public void delete(Jedis jedis, String key){
        String requestId = GetRedisRequestId.getRequestId();
        JedisLock.releaseDistributedLock(jedis, key, requestId);
    }

    public void remove(Jedis jedis){
        jedisConfig.jedisClose(jedis);
        GetRedisRequestId.remove();
    }

}