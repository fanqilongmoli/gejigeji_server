package com.gj.gejigeji;

import com.gj.gejigeji.model.*;
import com.gj.gejigeji.repository.*;
import com.gj.gejigeji.util.StringUtil;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GejigejiApplicationTests {

//    @Autowired
//    StringRedisTemplate redisTemplate;
//
//    @Autowired
//    RedisMessageListenerContainer redisMessageListenerContainer;

    @Autowired
    PropRepository propRepository;

    @Autowired
    AuctionChartRepository auctionChartRepository;

    @Autowired
    FeedRepository feedRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void delAll() {
//        SubscribeListener subscribeListener = new SubscribeListener();
//        subscribeListener.setStringRedisTemplate(redisTemplate);
//        //设置订阅topic
////        redisMessageListenerContainer.addMessageListener(subscribeListener, new ChannelTopic("123123123"));
//
//        PublishService publishService = SpringUtils.getBean(PublishService.class);
//        publishService.publish("fanqilong", "阿萨德哈沙师弟阿萨德");


    }

    @Test
    public void testMail() {
        final String compareable = StringUtil.compareable("5bed17284a51f954886c1c4d", "5bf13205fd6cb535791ae98c");
        System.out.println(compareable);


    }

    @Test
    public void addRes() {
        List<Feed> feeds = feedRepository.findAll();
        final Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());

        for (int i = 1; i <= 30; i++) {

            for (Feed feed : feeds) {

                instance.add(Calendar.DATE, -i);

                AuctionChart auctionChart = new AuctionChart();
                // v
                auctionChart.setVol(i);
                // h
                auctionChart.setHigh(i);
                // l
                auctionChart.setLow(i);
                // o
                auctionChart.setOpen(i);
                // c
                auctionChart.setClose(RandomUtils.nextInt(1, 100));
                // d
                auctionChart.setDate(instance.getTime());
                // feedId
                auctionChart.setFeedId(feed.getId());

                auctionChartRepository.save(auctionChart);

            }
        }
    }
}
