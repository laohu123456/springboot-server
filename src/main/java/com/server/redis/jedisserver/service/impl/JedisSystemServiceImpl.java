package com.server.redis.jedisserver.service.impl;

import com.server.redis.jedisserver.config.JedisConfig;
import com.server.redis.jedisserver.service.JedisSystemService;
import com.server.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class JedisSystemServiceImpl implements JedisSystemService {

    @Autowired
    private JedisConfig jedisConfig;

    @Override
    public Long findAllKeySize() {
        Jedis jedis = null;
        Long result = 0L;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.dbSize();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public boolean findExist(String key) {
        Jedis jedis = null;
        boolean result = Boolean.FALSE;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.exists(SerializationUtils.serialize(key));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long delKey(String... keys) {
        Jedis jedis = null;
        Long result = 0L;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.del(SerializationUtils.serialize(keys));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long setKeyExpire(String key, int times) {
        Jedis jedis = null;
        Long result = 0L;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.expire(SerializationUtils.serialize(key), times);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public String findKeyType(String key) {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.type(SerializationUtils.serialize(key));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public String reKeyName(String key, String newKeyName) {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.rename(SerializationUtils.serialize(key), SerializationUtils.serialize(newKeyName));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public String randomkey() {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.randomKey();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

/*    @Override
    public void scankey() {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.sca
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }*/

    @Override
    public String bgsave() {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.bgsave();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }
}
