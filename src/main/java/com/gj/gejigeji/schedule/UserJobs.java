package com.gj.gejigeji.schedule;

import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserChicken;
import com.gj.gejigeji.repository.UserChickenRepository;
import com.gj.gejigeji.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class UserJobs {

    private static final Logger logger = LoggerFactory.getLogger(UserJobs.class);


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserChickenRepository userChickenRepository;

    /**
     * 刷新用户的游戏次数
     */
    //@Scheduled(cron = "0 0 0 * * ?")
    //测试情况下设置30分钟刷新一次
    @Transactional
    //@Scheduled(fixedDelay = 20 * 60 * 1000)
    @Scheduled(cron = "0 0 0 * * ?")
    public void refreshGameCount() {
        logger.info("=======刷新用户的游戏次数开始========");
        List<User> all = userRepository.findAll();
        for (User user : all) {
            user.setMiniGameCount1(10);
            user.setMiniGameCount2(10);
            user.setMiniGameCount3(10);
            user.setMiniGameCount4(10);
            userRepository.save(user);
        }
        logger.info("=======刷新用户的游戏次数结束========");
    }

    /**
     * 刷新用户好感度
     * 后台逻辑和接口服务。亲密值总共100点。
     * 分配情况：抚摸10点，喂食30点，洗澡10点，玩耍45点，看电视5点。
     * 亲密度增加逻辑：抚摸一次2点，喂食一次10点，洗澡一次5点，玩耍一次3点，看电视一次1点。
     * 亲密度衰减逻辑：均以最近一次操作时间为基准，抚摸，每1小时衰减1点，喂食，每小时衰减2点，洗澡每小时衰减1点，玩耍每小时3点，看电视每小时1点。
     */
    @Transactional
    @Scheduled(fixedDelay = 1000 * 60)
    public void refreshLikeValue() {
        List<UserChicken> all = userChickenRepository.findAll();
        for (UserChicken userChicken : all) {
            //抚摸
            Date strokeLastTime = userChicken.getStrokeLastTime();
            if (Math.abs(calculateTimeDifferenceByCalendar(strokeLastTime, Calendar.HOUR)) >= 1) {
                int newStroke = userChicken.getStroke() - 1;
                userChicken.setStroke(newStroke);
                userChicken.setStrokeLastTime(new Date());
                logger.warn(userChicken.getUserId()+"===抚摸====亲密度衰减逻辑===>"+newStroke);
            }

            //喂食
             Date feedLastTime = userChicken.getFeedLastTime();
            if (Math.abs(calculateTimeDifferenceByCalendar(feedLastTime, Calendar.HOUR)) >= 1) {
                int newFeed = userChicken.getFeed() - 2;
                userChicken.setFeed(newFeed);
                userChicken.setFeedLastTime(new Date());
                logger.warn(userChicken.getUserId()+"===喂食====亲密度衰减逻辑===>"+newFeed);
            }
            //洗澡
            Date batheLastTime = userChicken.getBatheLastTime();
            if (Math.abs(calculateTimeDifferenceByCalendar(batheLastTime, Calendar.HOUR)) >= 1) {
                int newBathe = userChicken.getBathe() - 1;
                userChicken.setBathe(newBathe);
                userChicken.setBatheLastTime(new Date());
                logger.warn(userChicken.getUserId()+"===洗澡====亲密度衰减逻辑===>"+newBathe);
            }
            //玩耍
            Date gameLastTime = userChicken.getGameLastTime();
            if (Math.abs(calculateTimeDifferenceByCalendar(gameLastTime, Calendar.HOUR)) >= 1) {
                int newGame = userChicken.getGame() - 3;
                userChicken.setGame(newGame);
                userChicken.setGameLastTime(new Date());
                logger.warn(userChicken.getUserId()+"===玩耍====亲密度衰减逻辑===>"+newGame);
            }
            //打扫
            Date tvLastTime = userChicken.getTvLastTime();
            if (Math.abs(calculateTimeDifferenceByCalendar(tvLastTime, Calendar.HOUR)) >= 1) {
                int newTv = userChicken.getTv() - 1;
                userChicken.setTv(newTv);
                userChicken.setTvLastTime(new Date());
                logger.warn(userChicken.getUserId()+"===打扫====亲密度衰减逻辑===>"+newTv);
            }
            userChickenRepository.save(userChicken);

        }
    }


    private static int calculateTimeDifferenceByCalendar(Date lastDate, int get) {
        Calendar current = Calendar.getInstance();
        Calendar last = Calendar.getInstance();
        last.setTime(lastDate);

        int currentHour = current.get(get);
        int lastHour = last.get(get);

        return currentHour - lastHour;
    }
}
