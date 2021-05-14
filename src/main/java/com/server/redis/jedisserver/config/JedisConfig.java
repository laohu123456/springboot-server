package com.server.redis.jedisserver.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Configuration
public class JedisConfig {

    private JedisPool jedisPool = new JedisPool(setConfig(), "192.168.56.101" ,6379, 3000, "redis123");

    public Jedis getJedis(){
       return jedisPool.getResource();
    }

    public GenericObjectPoolConfig setConfig(){
        GenericObjectPoolConfig conf = new GenericObjectPoolConfig();
        conf.setMaxTotal(20);
        conf.setMaxIdle(20);
        conf.setMinIdle(10);
        conf.setTestWhileIdle(true);
        conf.setMinEvictableIdleTimeMillis(60000);
        conf.setTimeBetweenEvictionRunsMillis(30000);
        conf.setNumTestsPerEvictionRun(-1);
        return conf;
    }

    public void jedisClose(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

}
