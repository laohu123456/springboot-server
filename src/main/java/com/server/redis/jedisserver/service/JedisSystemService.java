package com.server.redis.jedisserver.service;

public interface JedisSystemService {


    public Long findAllKeySize();

    public boolean findExist(String key);

    public Long delKey(String...keys);

    public Long setKeyExpire(String key, int times);

    public String findKeyType(String key);

    public String reKeyName(String key, String newKeyName);

    public String randomkey();

    public void scankey();

    public String bgsave();

}
