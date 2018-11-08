package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.Feed;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserFeed;
import com.gj.gejigeji.repository.FeedRepository;
import com.gj.gejigeji.repository.UserFeedRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.vo.FeedBuyParam;
import com.gj.gejigeji.vo.FeedingParam;
import com.gj.gejigeji.vo.FeedingVo;
import com.gj.gejigeji.vo.OkResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    UserFeedRepository userFeedRepository;

    @Autowired
    UserRepository userRepository;

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
        // TODO: 2018/11/7 喂食 需要继续完成
        FeedingVo feedingVo = new FeedingVo();
        feedingVo.setAllow(true);
        feedingVo.setCount(10);
        feedingVo.setLikeValue(90);

        return feedingVo;

    }
}
