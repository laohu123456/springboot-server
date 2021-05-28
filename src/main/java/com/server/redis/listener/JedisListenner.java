package com.server.redis.listener;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

@Component
public class JedisListenner extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("channel:" + channel + " message:" + message);
    }



}
