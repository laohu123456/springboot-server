package com.server.redis.servcie;

public interface RedisSetService {


    void insertZset();

    boolean findExist(String path);

}
