package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.Feed;
import com.gj.gejigeji.model.UserFeed;
import com.gj.gejigeji.repository.FeedRepository;
import com.gj.gejigeji.repository.UserFeedRepository;
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

    public List<Feed> getAll() {
        return feedRepository.findAll();
    }

    public OkResult buy(FeedBuyParam feedBuyParam) {

        if (feedBuyParam.getAmount() > 10){
            throw new BaseRuntimeException("no.money");
        }
        Feed feedEx = new Feed();
        feedEx.setId(feedBuyParam.getFeedId());

        Feed feed = feedRepository.findOne(Example.of(feedEx)).orElse(null);
        if (feed==null){
            throw new BaseRuntimeException("no.feed");
        }
        Float price = feed.getPrice();

        UserFeed userFeed = new UserFeed();
        userFeed.setAllPrice(price*feedBuyParam.getAmount());
        userFeed.setFeedId(feedBuyParam.getFeedId());
        userFeed.setAmount(feedBuyParam.getAmount());
        userFeed.setPrice(price);
        userFeed.setUserId(feedBuyParam.getAccountID());
        userFeedRepository.save(userFeed);


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
