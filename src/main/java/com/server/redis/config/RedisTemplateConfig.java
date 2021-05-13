package com.server.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisTemplateConfig {


    @Autowired
    @Qualifier("redisListenerExpire")
    private MessageListener messageListener;

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisMessageListenerContainer container(MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisTemplate.getConnectionFactory());
        container.addMessageListener(listenerAdapter, new PatternTopic("__keyevent@0__:expired"));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(){
        return new MessageListenerAdapter(messageListener);
    }

}
