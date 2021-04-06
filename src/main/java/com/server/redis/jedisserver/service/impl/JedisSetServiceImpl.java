package com.server.redis.jedisserver.service.impl;

import com.server.redis.jedisserver.config.JedisConfig;
import com.server.redis.jedisserver.service.JedisSetService;
import com.server.utils.OtherUtils;
import com.server.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class JedisSetServiceImpl implements JedisSetService {

    @Autowired
    private JedisConfig jedisConfig;

    /**
     * set集合添加
     */
    @Override
    public Long sadd(String key, Object...object) {
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = jedisConfig.getJedis();
            byte[][] objectValue = OtherUtils.serializeArray(object);
            result = jedis.sadd(SerializationUtils.serialize(key), objectValue);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }


    /**
     * set集合删除
     */
    @Override
    public Long srem(String key, Object...object) {
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = jedisConfig.getJedis();
            byte[][] objectValue = OtherUtils.serializeArray(object);
            result = jedis.srem(SerializationUtils.serialize(key), objectValue);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }


    /**
     * set集合元素总个数
     */
    @Override
    public Long scard(String key) {
        Jedis jedis = null;
        Long result = null;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.scard(SerializationUtils.serialize(key));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }


    /**
     * set集合查询元素是否存在
     */
    @Override
    public Boolean sismember(String key, Object value) {
        Jedis jedis = null;
        Boolean result = Boolean.FALSE;
        try{
            jedis = jedisConfig.getJedis();
            result = jedis.sismember(SerializationUtils.serialize(key), SerializationUtils.serialize(value));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    /**
     * set集合弹出元素(不删除元素)
     */
    @Override
    public Object srandmember(String key) {
        Jedis jedis = null;
        Object result = null;
        try{
            jedis = jedisConfig.getJedis();
            byte[] bytes = jedis.srandmember(SerializationUtils.serialize(key));
            result = SerializationUtils.deserialize(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    /**
     * set集合弹出元素(不删除元素)
     */
    @Override
    public List<Object> srandmember(String key, int count) {
        Jedis jedis = null;
        List<Object> result = new ArrayList<>();
        try{
            jedis = jedisConfig.getJedis();
            List<byte[]> bytes = jedis.srandmember(SerializationUtils.serialize(key), count);
            if(!CollectionUtils.isEmpty(bytes)){
                for(byte[] bytes1: bytes){
                    result.add(SerializationUtils.deserialize(bytes1));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    /**
     * set集合弹出元素(删除元素)
     */
    @Override
    public Object spop(String key) {
        Jedis jedis = null;
        Object result = null;
        try{
            jedis = jedisConfig.getJedis();
            byte[] bytes = jedis.spop(SerializationUtils.serialize(key));
            result = SerializationUtils.deserialize(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    /**
     * set集合弹出元素(删除元素)
     */
    @Override
    public List<Object> spop(String key, long count) {
        Jedis jedis = null;
        List<Object> result = new ArrayList<>();
        try{
            jedis = jedisConfig.getJedis();
            Set<byte[]> bytes = jedis.spop(SerializationUtils.serialize(key), count);
            if(!CollectionUtils.isEmpty(bytes)){
                for(byte[] bytes1: bytes){
                    result.add(SerializationUtils.deserialize(bytes1));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    /**
     * set集合所有元素(慎用) 可以考虑sscan
     */
    @Override
    public List<Object> smembers(String key) {
        Jedis jedis = null;
        List<Object> result = new ArrayList<>();
        try{
            jedis = jedisConfig.getJedis();
            Set<byte[]> bytes = jedis.smembers(SerializationUtils.serialize(key));
            if(!CollectionUtils.isEmpty(bytes)){
                for(byte[] bytes1: bytes){
                    result.add(SerializationUtils.deserialize(bytes1));
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
    public List<Object> sinter(String... keys) {
        Jedis jedis = null;
        List<Object> result = new ArrayList<>();
        try{
            jedis = jedisConfig.getJedis();
            byte[][] bytes2 = OtherUtils.serializeArray(keys);
            Set<byte[]> bytes = jedis.sinter(bytes2);
            if(!CollectionUtils.isEmpty(bytes)){
                for(byte[] bytes1: bytes){
                    result.add(SerializationUtils.deserialize(bytes1));
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
    public List<Object>  sunion(String... keys) {
        Jedis jedis = null;
        List<Object> result = new ArrayList<>();
        try{
            jedis = jedisConfig.getJedis();
            byte[][] bytes2 = OtherUtils.serializeArray(keys);
            Set<byte[]> bytes = jedis.sunion(bytes2);
            if(!CollectionUtils.isEmpty(bytes)){
                for(byte[] bytes1: bytes){
                    result.add(SerializationUtils.deserialize(bytes1));
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
    public List<Object>  sdiff(String... keys) {
        Jedis jedis = null;
        List<Object> result = new ArrayList<>();
        try{
            jedis = jedisConfig.getJedis();
            byte[][] bytes2 = OtherUtils.serializeArray(keys);
            Set<byte[]> bytes = jedis.sdiff(bytes2);
            if(!CollectionUtils.isEmpty(bytes)){
                for(byte[] bytes1: bytes){
                    result.add(SerializationUtils.deserialize(bytes1));
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
    public Long sinterstore(String newKey, String... keys) {
        Jedis jedis = null;
        Long result = 0L;
        try{
            jedis = jedisConfig.getJedis();
            byte[][] bytes2 = OtherUtils.serializeArray(keys);
            result = jedis.sinterstore(SerializationUtils.serialize(newKey), bytes2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long sunionstore(String newKey, String... keys) {
        Jedis jedis = null;
        Long result = 0L;
        try{
            jedis = jedisConfig.getJedis();
            byte[][] bytes2 = OtherUtils.serializeArray(keys);
            result = jedis.sunionstore(SerializationUtils.serialize(newKey), bytes2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public Long sdiffstore(String newKey, String... keys) {
        Jedis jedis = null;
        Long result = 0L;
        try{
            jedis = jedisConfig.getJedis();
            byte[][] bytes2 = OtherUtils.serializeArray(keys);
            result = jedis.sdiffstore(SerializationUtils.serialize(newKey), bytes2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
        return result;
    }

    @Override
    public void sscan(String key, String matchStr, int count) {
        Jedis jedis = null;
        Long result = 0L;
        try{
            jedis = jedisConfig.getJedis();
            //jedis.sscan();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
       // return result;
    }
}
