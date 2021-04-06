package com.server.redis.jedisserver.service;

import java.util.List;

public interface JedisSetService {

    public Long sadd(String key, Object...object);

    public Long srem(String key, Object...object);

    public Long scard(String key);

    public Boolean sismember(String key, Object value);

    public Object srandmember(String key);

    public List<Object> srandmember(String key, int count);

    public Object spop(String key);

    public List<Object> spop(String key, long count);

    public List<Object> smembers(String key);

    public List<Object>  sinter(String...keys);

    public List<Object>  sunion(String...keys);

    public List<Object>  sdiff(String...keys);

    public Long sinterstore(String newKey, String...keys);

    public Long sunionstore(String newKey, String...keys);

    public Long sdiffstore(String newKey, String...keys);

    public void sscan(String key, String matchStr, int count);

}
