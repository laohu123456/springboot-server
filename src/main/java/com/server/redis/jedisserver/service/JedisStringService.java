package com.server.redis.jedisserver.service;

import java.util.List;

public interface JedisStringService {

    //增、删、改、查
    public String add(String key, String value);

    public Long addNx(String key, String value);

    public String addArray(String...keyValues);

    public String getValue(String key);

    public List<String> getValues(String...key);

    public Long incrByIncrement(String key, Long increment);

    public Long incr(String key);

    public Long decrByIncrement(String key, Long increment);

    public Long decr(String key);

    public Long appendValue(String key, String appendValue);

    public Long getStrLength(String key);

    public String getSet(String key, String value);

    public Long setStrByindex(String key, Long index, String value);

    public String getRangeStr(String key, Long start, Long end);

}
