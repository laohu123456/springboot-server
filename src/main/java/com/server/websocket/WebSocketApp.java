package com.server.websocket;


import com.server.redis.jedisserver.service.JedisSetService;
import com.server.utils.GetSpringBean;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class WebSocketApp {
    /**
     * 单机时怎么搞都行
     *
     * 双活或者大集群时，怎么样，将Session保存一个共有的容器中
     * 但是Session无法序列化
     *
     *  本地Map<sesssionId, session>
     *  将sessionID和 本台机器的IP PORT 组合成一个value  存入redis的List, set数据结构中
     *  服务端写一个接口,感知到发消息就进行任务调度，轮询Redis数据结构并发送消息
     *  发送消息方式 HttpClient ( 解析出IP,PORT + 接口名称，传递消息到指定服务器,消息到达后，后续任务交由webSocket执行)
     *
     *  任务调度的话， 可以选择，定时器，redis监听器，或者什么组件的触发器
     *
     */
    public static Map<String, WebSocketApp> map = new ConcurrentHashMap<>();

    private final static String REDIS_KEY_WEBSOCKET = "redis_key_websocket";

    private Session session;

    private String userId;

    private JedisSetService service =(JedisSetService) GetSpringBean.getBean("jedisSetServiceImpl");

    //__keyevent@0__:expired
    @OnOpen
    public void open(Session session, @PathParam("userId") String userId){
        System.out.println(session);
        System.out.println(userId);

        this.session = session;
        this.userId = userId;
        map.put(userId, this);
        WebSocketPojo webSocketPojo = new WebSocketPojo(userId);
        service.sadd(REDIS_KEY_WEBSOCKET, webSocketPojo);
    }

    @OnClose
    public void close(){
        //WebSocketPojo webSocketPojo = new WebSocketPojo(userId, session);
       // service.srem(REDIS_KEY_WEBSOCKET, webSocketPojo);
        map.remove(userId);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") String userId){
        System.out.println(message);
    }

    @OnError
    public void error(Session session, Throwable error){

    }


}
