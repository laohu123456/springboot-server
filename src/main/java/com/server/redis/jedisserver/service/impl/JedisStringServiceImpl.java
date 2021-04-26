package com.server.redis.jedisserver.service.impl;

import com.server.redis.jedisserver.config.JedisConfig;
import com.server.redis.jedisserver.service.JedisStringService;
import com.server.utils.OtherUtils;
import com.server.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import sun.misc.PostVMInitHook;

import java.util.ArrayList;
import java.util.List;

@Service
public class JedisStringServiceImpl implements JedisStringService {

    @Autowired
    private JedisConfig jedisConfig;

    @Override
    public String add(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.set(SerializationUtils.serialize(key), SerializationUtils.serialize(value));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long addNx(String key, String value) {
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.setnx(SerializationUtils.serialize(key), SerializationUtils.serialize(value));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public String addArray(String...keyValues) {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            byte[][] key_Value = OtherUtils.serializeArray(keyValues);
            result = jedis.mset(key_Value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public String getValue(String key) {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            byte[] bytes = jedis.get(SerializationUtils.serialize(key));
            result = String.valueOf(SerializationUtils.deserialize(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public List<String> getValues(String... keys) {
        Jedis jedis = null;
        List<String> result = new ArrayList<>();
        try{
            jedis = jedisConfig.getJedis();
            byte[][] key = OtherUtils.serializeArray(keys);
            List<byte[]> list =  jedis.mget(key);
            if(!CollectionUtils.isEmpty(list)){
                for(byte[] bytes:list){
                    result.add(String.valueOf(SerializationUtils.deserialize(bytes)));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long incrByIncrement(String key, Long increment) {
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.incrBy(SerializationUtils.serialize(key),increment);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.incr(SerializationUtils.serialize(key));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long decrByIncrement(String key, Long increment) {
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.decrBy(SerializationUtils.serialize(key), increment);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long decr(String key) {
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.decr(SerializationUtils.serialize(key));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long appendValue(String key, String appendValue) {
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.append(SerializationUtils.serialize(key), SerializationUtils.serialize(appendValue));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long getStrLength(String key) {
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.strlen(SerializationUtils.serialize(key));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public String getSet(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            byte[] bytes = jedis.getSet(SerializationUtils.serialize(key), SerializationUtils.serialize(value));
            result = String.valueOf(SerializationUtils.deserialize(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long setStrByindex(String key, Long index, String value) {
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.setrange(SerializationUtils.serialize(key), index, SerializationUtils.serialize(value));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public String getRangeStr(String key, Long start, Long end) {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            byte[] bytes = jedis.getrange(SerializationUtils.serialize(key), start, end);
            result = String.valueOf(SerializationUtils.deserialize(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }



}
