package com.server.redis.jedisserver.config;

import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {

    public Jedis getJedis(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMinIdle(10);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.56.101" ,6379, 3000, "redis123");
        return jedisPool.getResource();
    }

    public void jedisClose(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

}
