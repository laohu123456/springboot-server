package com.server.redis.lock;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JedisLock {

    //加锁成功标志
    private static final String LOCK_SUCCESS = "OK";
    //解锁成功标志
    private final static Long RELEASE_SUCCESS = 1L;

    private final static String SET_IF_NOT_EXIST = "NX";
    private final static String SET_WITH_EXPIRE_TIME = "PX";

    // 加锁脚本
    private final static String SCRIPT_LOCK = "if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then redis.call('pexpire', KEYS[1], ARGV[2]) return 1 else return 0 end";
    // 解锁脚本
    private final static String SCRIPT_UNLOCK = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";


    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }


    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        Object result = jedis.eval(SCRIPT_UNLOCK, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    /**
     * 分布式锁续租
     */
    public static boolean expandLockTime(Jedis jedis,String lockKey, String requestId, Long expireTime){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('expire', KEYS[1],ARGV[2]) else return '0' end";
        List<String> list = new ArrayList<String>();
        list.add(requestId);
        list.add(String.valueOf(expireTime));
        Object result = jedis.eval(script, Collections.singletonList(lockKey), list);
        if(RELEASE_SUCCESS.equals(result)){
            return true;
        }
        return false;
    }



}
