package com.server.redis.listener;

import com.server.utils.SerializationUtils;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisListenerExpire implements MessageListener {




    /**
     * 开启过期Key监听， 需要配置
     * notify-keyspace-events Ex “ex”是事件标志，“”代表禁用
     * psubscribe 订阅频道 __keyevent@0__:expired
     * 并非key过期事件就触发，多少比到期时间慢点(key过期在前,触发事件在后，并非同一时刻)
     */

    /**
     *
     * @param message  过期的key
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        Object deserialize = SerializationUtils.deserialize(message.getBody());
        System.out.println(String.valueOf(deserialize));
    }


}
