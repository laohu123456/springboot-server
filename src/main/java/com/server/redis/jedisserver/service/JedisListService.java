package com.server.redis.jedisserver.service;

import redis.clients.jedis.BinaryClient;

import java.util.List;

public interface JedisListService {


    public Long rpush(String key, Object object);

    public Long lpush(String key, Object object);

    public Long linsert(String key, BinaryClient.LIST_POSITION listPosition , String tarSource, Object value);

    public List<String> lrange(String key, long start, long end);

    public String lindex(String key, long index);

    public Long llen(String key);

    public String lpop(String key);

    public String rpop(String key);

    public Long lrem(String key, long count, String value);

    public String ltrim(String key, long start, long end);

    public String lset(String key, long index, Object value);

    public List<String> blpop(int timeout, List<String> list);

    public List<String> brpop(int timeout, List<String> list);


}
