package com.gj.gejigeji.chat.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class PublishService {

    /**
     * form to time content msgType
     */

    @Autowired
    StringRedisTemplate redisTemplate;

    public void publish(String channel, Object message) {
        // 该方法封装的 connection.publish(rawChannel, rawMessage);
        redisTemplate.convertAndSend(channel, message);
    }
}
