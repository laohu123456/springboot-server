package com.server.redis.servcie;

public interface RedisStringService {

    public void add(String key, String Value);  //增加

    public boolean delete(String key); //删除

    public void update(String key, String Value); //修改

    public boolean exist(String key);  //查询

    public Long ttl(String key);  //查询剩余时间

    public void expire(String key, String value, Long times); //添加过期时间

    public Object get(String key); //获得对应value值

    public Long increNum(String key); //获得对应key相当访问次数

}
