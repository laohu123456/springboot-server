package com.server.scheduled;


import com.server.redis.jedisserver.config.JedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Random;

@Component
public class SendExpireEvent {


    @Autowired
    private JedisConfig jedisConfig;


    private final static String[] DX_ZM = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"
            ,"P","Q","R","S","T","U","V","W","X","Y","Z"};

    @Scheduled(fixedDelay = 1000)
    public void sendExpireEvent(){
     //   setExpireMethod();
    }
    private void setExpireMethod(){
        Random random = new Random();
        int randomNum = random.nextInt(25);
        String randomStr = DX_ZM[randomNum];
        Jedis jedis = jedisConfig.getJedis();
        setExpire(jedis, randomStr, randomNum);
    }

    public void setExpire(Jedis jedis, String randomStr, int randomNum){
        try {
            jedis.set(randomStr, "");
            jedis.expire(randomStr, randomNum);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedisConfig.jedisClose(jedis);
        }
    }




}
