package com.server.redis.lock;

import com.server.config.AllException;
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


    /**
     * 待考量
     * 利用全局异常捕获，判断是否获取锁成功,获取不成成功抛出异常 throw new AllException(500,"获取资源失败");
     *
     * @Before 加锁  一定要设置过期时间 并且开始 另外线程开始续租工作
     * @After 释放锁
     * @AfterThrowing  系统异常释放锁
     */



    @Autowired
    private JedisConfig jedisConfig;

    @Pointcut("@annotation(com.server.redis.lock.RedisLockAnnotation)")
    public void redisLockAnnotationImpl(){}

    @Before("redisLockAnnotationImpl()")
    public void before(JoinPoint joinPoint) throws AllException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RedisLockAnnotation annotation = method.getAnnotation(RedisLockAnnotation.class);
        int expireTime = annotation.expireTime();
        String key = annotation.key();
        throw  new AllException(500,"服务器异常");

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
