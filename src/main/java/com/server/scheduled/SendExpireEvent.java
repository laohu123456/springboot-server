package com.server.scheduled;


import com.server.redis.jedisserver.service.JedisStringService;
import com.server.redis.jedisserver.service.JedisSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SendExpireEvent {


    @Autowired
    private JedisStringService jedisStringService;

    @Autowired
    private JedisSystemService jedisSystemService;

    private final static String[] DX_ZM = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"
            ,"P","Q","R","S","T","U","V","W","X","Y","Z"};

    @Scheduled(fixedDelay = 1000)
    public void sendExpireEvent(){
        Random random = new Random();
        int randomNum = random.nextInt(25);
        String randomStr = DX_ZM[randomNum];
        jedisStringService.add(randomStr, "");
        jedisSystemService.setKeyExpire(randomStr, randomNum);
    }

}
