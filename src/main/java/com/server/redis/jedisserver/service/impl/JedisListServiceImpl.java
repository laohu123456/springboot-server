package com.server.redis.jedisserver.service.impl;

import com.server.redis.jedisserver.config.JedisConfig;
import com.server.redis.jedisserver.service.JedisListService;
import com.server.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class JedisListServiceImpl implements JedisListService {

    @Autowired
    private JedisConfig jedisConfig;


    @Override
    public Long rpush(String key, Object object) {
        Jedis jedis = null;
        Long result = 0L;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.rpush(SerializationUtils.serialize(key), SerializationUtils.serialize(object));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long lpush(String key, Object object) {
        Jedis jedis = null;
        Long result = 0L;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.lpush(SerializationUtils.serialize(key), SerializationUtils.serialize(object));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long linsert(String key, BinaryClient.LIST_POSITION listPosition, String tarSource, Object value) {
        Jedis jedis = null;
        Long result = 0L;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.linsert(SerializationUtils.serialize(key), listPosition, SerializationUtils.serialize(tarSource),SerializationUtils.serialize(value));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = null;
        List<String> result_list = new ArrayList<>();
        try{
            jedis = jedisConfig.getJedis();
            List<byte[]> list = jedis.lrange(SerializationUtils.serialize(key), start, end);
            if(!CollectionUtils.isEmpty(list)){
                for(byte[] bytes: list){
                    result_list.add(String.valueOf(SerializationUtils.deserialize(bytes)));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result_list;
    }

    @Override
    public String lindex(String key, long index) {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            byte[] bytes = jedis.lindex(SerializationUtils.serialize(key), index);
            Object deserialize = SerializationUtils.deserialize(bytes);
            result = String.valueOf(deserialize);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long llen(String key) {
        Jedis jedis = null;
        Long result = 0L;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.llen(SerializationUtils.serialize(key));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public String lpop(String key) {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            byte[] bytes = jedis.lpop(SerializationUtils.serialize(key));
            result = String.valueOf(SerializationUtils.deserialize(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public String rpop(String key) {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            byte[] bytes = jedis.rpop(SerializationUtils.serialize(key));
            result = String.valueOf(SerializationUtils.deserialize(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long lrem(String key, long count, String value) {
        Jedis jedis = null;
        Long result = 0L;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.lrem(SerializationUtils.serialize(key), count, SerializationUtils.serialize(value));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public String ltrim(String key, long start, long end) {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.ltrim(SerializationUtils.serialize(key), start, end);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public String lset(String key, long index, Object value) {
        Jedis jedis = null;
        String result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.lset(SerializationUtils.serialize(key), index, SerializationUtils.serialize(value));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public List<String> blpop(int timeout, List<String> list) {
        Jedis jedis = null;
        List<String> result = new ArrayList<>();
        try{
            byte[][] bytes = null;
            jedis = jedisConfig.getJedis();
            if(!CollectionUtils.isEmpty(list)){
                bytes = new byte[list.size()][];
                int i = 0;
                for(String str:list){
                    bytes[i] = SerializationUtils.serialize(str);
                    i++;
                }
            }
            List<byte[]> blpop = jedis.blpop(timeout, bytes);
            if (!CollectionUtils.isEmpty(blpop)) {
                for(byte[] by : blpop){
                    result.add(String.valueOf(SerializationUtils.deserialize(by)));
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
    public List<String> brpop(int timeout, List<String> list) {
        Jedis jedis = null;
        List<String> result = new ArrayList<>();
        try{
            byte[][] bytes = null;
            jedis = jedisConfig.getJedis();
            if(!CollectionUtils.isEmpty(list)){
                bytes = new byte[list.size()][];
                int i = 0;
                for(String str:list){
                    bytes[i] = SerializationUtils.serialize(str);
                    i++;
                }
            }
            List<byte[]> blpop = jedis.brpop(timeout, bytes);
            if (!CollectionUtils.isEmpty(blpop)) {
                for(byte[] by : blpop){
                    result.add(String.valueOf(SerializationUtils.deserialize(by)));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }
}
