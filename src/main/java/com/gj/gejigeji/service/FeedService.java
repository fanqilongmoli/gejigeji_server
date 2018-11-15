package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.Feed;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserFeed;
import com.gj.gejigeji.model.UserLikeValue;
import com.gj.gejigeji.repository.FeedRepository;
import com.gj.gejigeji.repository.UserFeedRepository;
import com.gj.gejigeji.repository.UserLikeValueRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FeedService {

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    UserFeedRepository userFeedRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserLikeValueRepository userLikeValueRepository;

    public List<Feed> getAll() {
        return feedRepository.findAll();
    }

    public OkResult buy(FeedBuyParam feedBuyParam) {

        User userEx = new User();
        userEx.setId(feedBuyParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null){
            throw new BaseRuntimeException("login.user.null");
        }

        Feed feedEx = new Feed();
        feedEx.setId(feedBuyParam.getFeedId());
        Feed feed = feedRepository.findOne(Example.of(feedEx)).orElse(null);
        //检查 饲料是否存在
        if (feed == null) {
            throw new BaseRuntimeException("no.feed");
        }
        //检查购买的个数 是否大于最小购买个数
        if (feedBuyParam.getAmount()<feed.getMin()){
            throw new BaseRuntimeException("less.than.min");
        }



        Float resultCoin = user.getCoin()-feedBuyParam.getAmount()*feed.getPrice();

        if (resultCoin<0){
            throw new BaseRuntimeException("no.money");
        }
        // 更新用户金币数
        user.setCoin(resultCoin);
        userRepository.save(user);

        UserFeed ex = new UserFeed();
        ex.setUserId(feedBuyParam.getAccountID());
        ex.setFeedId(feedBuyParam.getFeedId());
        UserFeed userFeed = userFeedRepository.findOne(Example.of(ex)).orElse(null);
        if (userFeed == null){
            userFeed = new UserFeed();
            userFeed.setUserId(feedBuyParam.getAccountID());
            userFeed.setFeedId(feedBuyParam.getFeedId());
            userFeed.setAmount(feedBuyParam.getAmount());
            userFeed.setPrice(feed.getPrice());
            userFeed.setAllPrice(feed.getPrice()*feedBuyParam.getAmount());
            userFeedRepository.save(userFeed);
        }else {

            userFeed.setAmount(userFeed.getAmount()+feedBuyParam.getAmount());
            userFeed.setAllPrice(userFeed.getAllPrice()+feed.getPrice()*feedBuyParam.getAmount());

            userFeedRepository.save(userFeed);
        }


        return new OkResult(true);
    }

    public OkResult feedTest(String userId) {

        List<Feed> all = feedRepository.findAll();
        for (Feed feed : all) {
            Float price = feed.getPrice();

            UserFeed userFeed = new UserFeed();
            userFeed.setAllPrice(price*10);
            userFeed.setFeedId(feed.getId());
            userFeed.setAmount(10);
            userFeed.setPrice(price);
            userFeed.setUserId(userId);
            userFeedRepository.save(userFeed);
        }
        return new OkResult(true);

    }

    public FeedingVo feeding(FeedingParam feedingParam) {

        //查询用户饲料表
        UserFeed userFeedEx = new UserFeed();
        userFeedEx.setId(feedingParam.getFeedID());
        userFeedEx.setUserId(feedingParam.getAccountID());

        UserFeed userFeed = userFeedRepository.findOne(Example.of(userFeedEx)).orElse(null);
        FeedingVo feedingVo = new FeedingVo();
        // 用户不存在这个饲料 或者 用户该种饲料 个数为0
        if (userFeed == null || userFeed.getAmount() == 0){

            feedingVo.setAllow(false);
            feedingVo.setCount(0);
            return feedingVo;
        }

        //减去用户饲料的数量 保存
        userFeed.setAmount(userFeed.getAmount()-1);
        userFeedRepository.save(userFeed);

        //修改用户的好感度
        UserLikeValue userLikeValueEx = new UserLikeValue();
        userLikeValueEx.setUserId(feedingParam.getAccountID());
        UserLikeValue userLikeValue = userLikeValueRepository.findOne(Example.of(userLikeValueEx)).orElse(null);
        if (userLikeValue!=null){

            userLikeValue.setFeed(userLikeValue.getFeed() + 10);
            userLikeValue.setFeedLastTime(new Date());
            userLikeValueRepository.save(userLikeValue);

            feedingVo.setLikeValue(userLikeValue.getFeed() + userLikeValue.getStroke() + userLikeValue.getBathe() + userLikeValue.getGame() + userLikeValue.getTv());
            feedingVo.setAllow(true);
            feedingVo.setCount(userFeed.getAmount());

            return feedingVo;
        }

        feedingVo.setAllow(false);
        feedingVo.setCount(0);
        return feedingVo;

    }

    public List<FeedListVo> get(ActionParam actionParam) {

        List<FeedListVo> feedListVos = new ArrayList<>();
        UserFeed ex = new UserFeed();
        ex.setUserId(actionParam.getAccountID());

        List<UserFeed> all = userFeedRepository.findAll(Example.of(ex));
        FeedListVo feedListVo;
        for (UserFeed userFeed : all) {
            Feed feedEx = new Feed();
            feedEx.setId(userFeed.getFeedId());
            Feed feed = feedRepository.findOne(Example.of(feedEx)).orElse(null);
            if (feed != null){
                feedListVo = new FeedListVo();
                feedListVo.setUrl(feed.getUrl());
                feedListVo.setAmount(userFeed.getAmount());
                feedListVo.setDesc(feed.getDesc());
                feedListVo.setFeedId(feed.getId());
                feedListVo.setName(feed.getName());
                feedListVo.setPrice(feed.getPrice());

                feedListVos.add(feedListVo);
            }
        }

        return feedListVos;
    }
}
