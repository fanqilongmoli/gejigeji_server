package com.gj.gejigeji.chat.websocket;

import com.fasterxml.jackson.core.JsonParser;
import com.gj.gejigeji.chat.listener.PublishService;
import com.gj.gejigeji.chat.listener.SubscribeListener;
import com.gj.gejigeji.util.*;
import com.gj.gejigeji.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint(value = "/websocket/{accountID}")
public class WebsocketEndpoint {

    private RedisUtil redisUtil = SpringUtils.getBean(RedisUtil.class);

    //    @Autowired
//    RedisMessageListenerContainer redisMessageListenerContainer;
    private RedisMessageListenerContainer redisMessageListenerContainer = SpringUtils.getBean(RedisMessageListenerContainer.class);

    //存放该服务器该ws的所有连接。用处：比如向所有连接该ws的用户发送通知消息。
    private static CopyOnWriteArraySet<WebsocketEndpoint> sessions = new CopyOnWriteArraySet<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("accountID") String accountID) {
        System.out.println(accountID + " websocket:打开连接");
        this.session = session;
        sessions.add(this);
        SubscribeListener subscribeListener = new SubscribeListener();
        subscribeListener.setSession(session);
        subscribeListener.setRedisTemplate(redisUtil.getRedisTemplate());
        //聊天socket链接 订阅本身
        redisMessageListenerContainer.addMessageListener(subscribeListener, new ChannelTopic(accountID));
    }

    @OnClose
    public void onClose(Session session, @PathParam("accountID") String accountID) {
        System.out.println(accountID + " websocket:关闭连接");
        sessions.remove(this);
        // 清除用户的频道监听
        redisMessageListenerContainer.removeMessageListener(null,new ChannelTopic(accountID));

    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("accountID") String accountID) throws IOException {

        System.out.println(accountID+"websocket 发送消息==" + message);
        MessageVo messageVo = JsonUtil.deserialize(message, MessageVo.class);
        messageVo.setStatus(ConstUtil.MSG_READ);
        //添加时间
        messageVo.setCreateTime(new Date());
        PublishService publishService = SpringContextHolder.getBean(PublishService.class);
        // 发送消息给 to  消息内容 content
        publishService.publish(messageVo.getTo(), JsonUtil.serialize(messageVo));
    }

    @OnError
    public void onError(Session session, @PathParam("accountID") String accountID, Throwable error) {
        sessions.remove(this);
        // 清除用户的频道监听
        redisMessageListenerContainer.removeMessageListener(null,new ChannelTopic(accountID));
        System.out.println(accountID + "websocket 出现错误" + error.getMessage());
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
