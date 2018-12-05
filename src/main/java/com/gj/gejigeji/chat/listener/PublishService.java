package com.gj.gejigeji.chat.listener;

import com.gj.gejigeji.model.Message;
import com.gj.gejigeji.repository.MessageRepository;
import com.gj.gejigeji.util.ConstUtil;
import com.gj.gejigeji.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PublishService {

    /**
     * form to time content msgType
     */

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    MessageRepository messageRepository;

    public void publish(String channel, String message) throws IOException {
        // 该方法封装的 connection.publish(rawChannel, rawMessage);
        // redisTemplate.convertAndSend(channel, message);
        final RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        if (connectionFactory != null) {
            Long publish = connectionFactory.getConnection().publish(channel.getBytes(), message.getBytes());

            Message msg = JsonUtil.deserialize(message, Message.class);

            if (publish < 1) {
                // 订阅者不存在  消息存入为未读
                msg.setStatus(ConstUtil.MSG_UNREAD);
            } else {
                // 订阅者存在  消息存入为已读
                msg.setStatus(ConstUtil.MSG_READ);
            }
            //保存到聊天记录
            messageRepository.save(msg);
        }
    }
}
