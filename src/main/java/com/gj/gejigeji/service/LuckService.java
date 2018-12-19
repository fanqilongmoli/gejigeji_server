package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.LuckNoNumException;
import com.gj.gejigeji.exception.NoUserException;
import com.gj.gejigeji.model.Feed;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserFeed;
import com.gj.gejigeji.model.UserLuck;
import com.gj.gejigeji.repository.FeedRepository;
import com.gj.gejigeji.repository.UserFeedRepository;
import com.gj.gejigeji.repository.UserLuckRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.util.ConstUtil;
import com.gj.gejigeji.vo.LuckParam;
import com.gj.gejigeji.vo.LuckVo;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LuckService {

    @Autowired
    UserLuckRepository userLuckRepository;

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFeedRepository userFeedRepository;


    /**
     * 检查游戏次数 一天三次
     *
     * @param accountId
     * @return
     */
    public List<UserLuck> getLuckNum(String accountId) {
        List<UserLuck> byUserIdAndDeleteFlag = userLuckRepository.findByUserIdAndDeleteFlag(accountId, ConstUtil.Delete_Flag_No);
        return byUserIdAndDeleteFlag;
    }


    /**
     * 抽奖
     *
     * @param luckParam
     * @return
     */
    public LuckVo luck(LuckParam luckParam) {

        User user = userRepository.findById(luckParam.getAccountID()).orElse(null);
        if (user == null) {
            throw new NoUserException();
        }
        // 检查游戏次数
        List<UserLuck> userLucks = userLuckRepository.findByUserIdAndDeleteFlag(luckParam.getAccountID(), ConstUtil.Delete_Flag_No);
        if (userLucks.size() >= 3) {
            throw new LuckNoNumException();
        }

        UserLuck userLuck = new UserLuck();
        userLuck.setOpen(luckParam.getOpen());
        userLuck.setUserId(luckParam.getAccountID());
        userLuck.setCreateTime(new Date());
        userLuck.setDeleteFlag(ConstUtil.Delete_Flag_No);

        LuckVo luckVo = new LuckVo();
        //抽奖 一天三次 概率30%
        int i = RandomUtils.nextInt(1, 11);
        if (i <= 3) {
            //中奖 随机中奖类型
            int i1 = RandomUtils.nextInt(1, 4);
            userLuck.setLuck(true);
            luckVo.setLuckType(i1);
            luckVo.setLuck(true);

            switch (i1) {
                case 1:
                    //金币 继续算计个数 1-10
                    final int coin = RandomUtils.nextInt(1, 11);

                    //用户增加 金币数量
                    user.setCoin(user.getCoin() + coin);
                    userRepository.save(user);

                    luckVo.setNum(coin);
                    break;
                case 2:
                    //钻石 暂时 0.1个

                    //随机产生钻石的个数 0.1-0.5
                    int jewel = RandomUtils.nextInt(1, 6) / 10;
                    //用户增加 钻石数量
                    user.setJewel(user.getJewel() + jewel / 10);
                    userRepository.save(user);

                    luckVo.setNum(jewel / 10);
                    break;
                case 3:
                    //饲料 继续随机获取饲料的类型
                    List<Feed> feeds = feedRepository.findAll();
                    int feedIndex = RandomUtils.nextInt(0, feeds.size());
                    Feed feedSelect = feeds.get(feedIndex);

                    //继续随机选择 饲料的数量 1-5 份
                    int feedNum = RandomUtils.nextInt(1, 6);

                    //用户增加饲料数量
                    UserFeed userFeed = userFeedRepository.findByUserIdAndFeedId(user.getId(), feedSelect.getId()).orElse(null);
                    if (userFeed == null) {
                        userFeed = new UserFeed();
                        userFeed.setUserId(user.getId());
                        userFeed.setFeedId(feedSelect.getId());
                        userFeed.setAmount(feedNum);
                        userFeed.setPrice(feedSelect.getPrice());
                    } else {
                        userFeed.setAmount(userFeed.getAmount() + feedNum);
                    }
                    userFeedRepository.save(userFeed);


                    luckVo.setNum(feedNum);
                    luckVo.setUrl(feedSelect.getUrl());
                    luckVo.setName(feedSelect.getName());
                    break;
            }

        } else {
            userLuck.setLuck(false);
            luckVo.setLuck(false);

            userLuckRepository.save(userLuck);
        }


        return luckVo;
    }
}
