package com.server.redis.config;

import com.server.constant.CommonConstant;
import com.server.redis.listener.RedisListenerExpire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisTemplateConfig {



    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisMessageListenerContainer container(MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisTemplate.getConnectionFactory());
        container.addMessageListener(listenerAdapter, new PatternTopic(CommonConstant.getRedisKeyEventExpire()));
        return container;
    }

   // @Bean
   // public MessageListenerAdapter listenerAdapter(RedisListenerExpire expire){
  //      return new MessageListenerAdapter(expire, "expire");
  //  }

    @Bean
    public MessageListenerAdapter listenerAdapter(RedisListenerExpire expire){
        return new MessageListenerAdapter(expire);
    }



}
