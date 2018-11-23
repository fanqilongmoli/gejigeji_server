package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.exception.NoEggException;
import com.gj.gejigeji.exception.NoOrderException;
import com.gj.gejigeji.model.Feed;
import com.gj.gejigeji.model.Order;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserEgg;
import com.gj.gejigeji.repository.FeedRepository;
import com.gj.gejigeji.repository.OrderRepository;
import com.gj.gejigeji.repository.UserEggRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.util.ConstUtil;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.PageParam;
import com.gj.gejigeji.vo.PlaceParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserEggRepository userEggRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedRepository feedRepository;

    /**
     * 下订单
     *
     * @param placeParam
     * @return
     */
    public OkResult place(PlaceParam placeParam) {

        User user = userRepository.findById(placeParam.getAccountID()).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        //检查用户的鸡蛋数量够不够
        UserEgg userEggEx = new UserEgg();
        userEggEx.setUserId(placeParam.getAccountID());
        userEggEx.setFeedId(placeParam.getFeedId());

        UserEgg userEgg = userEggRepository.findOne(Example.of(userEggEx)).orElse(null);
        if (userEgg == null || (userEgg.getAmount() - userEgg.getFreeze()) < placeParam.getAmount()) {
            throw new NoEggException();
        }


        Feed feedEx = new Feed();
        feedEx.setId(placeParam.getFeedId());
        Feed feed = feedRepository.findOne(Example.of(feedEx)).orElse(null);
        //保存订单
        Order order = new Order();
        if (feed!=null) {
            order.setEggName(feed.getEggName());
        }
        order.setAmount(placeParam.getAmount());
        order.setUserId(placeParam.getAccountID());
        order.setCreateTime(new Date());
        order.setPrice(placeParam.getPrice());
        order.setAllPrice(placeParam.getAmount() * placeParam.getPrice());
        order.setUserName(user.getUserName());
        order.setFeedId(placeParam.getFeedId());
        order.setOrderState(ConstUtil.Order_Open);
        order.setVolume(0);
        orderRepository.save(order);

        //冻结用户鸡蛋
        userEgg.setFreeze(userEgg.getFreeze() + placeParam.getAmount());
        userEggRepository.save(userEgg);

        return new OkResult(true);
    }

    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    public OkResult submitCancel(String accountID,String orderId) {

        //检查订单是否存在
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null){
            throw new NoOrderException();
        }

        //检查用户鸡蛋 是否存在
        UserEgg userEgg = userEggRepository.findUserEggByUserIdAndFeedId(accountID, order.getFeedId()).orElse(null);
        if (userEgg == null){

            throw new NoEggException();
        }


        //修改取消状态
        if (order.getOrderState().equals(ConstUtil.Order_Open)) {
            order.setOrderState(ConstUtil.Order_Close);
        }else {
            order.setOrderState(ConstUtil.Order_Close_Finish_Part);
        }

        orderRepository.save(order);
        //减去用户冻结的鸡蛋数
        userEgg.setFreeze(userEgg.getFreeze()-order.getAmount());
        userEggRepository.save(userEgg);

        return new OkResult(true);
    }

    /**
     * 获取用户未完成订单
     *
     * @param accountID
     * @param pageParam
     * @return
     */
    public Page<Order> openOrders(String accountID, PageParam pageParam) {
        Pageable pageable = PageRequest.of(pageParam.getPage(), pageParam.getSize());
        List<Byte> bytes = new ArrayList<>();
        bytes.add(ConstUtil.Order_Open);
        bytes.add(ConstUtil.Order_Open_Finish_Part);
        Page<Order> all = orderRepository.findByUserIdAndOrderStateInOrderByCreateTimeDesc(pageable,accountID,bytes);
        return all;
    }

    /**
     *
     * @param pageParam
     * @return
     */
    public Page<Order> orders(PageParam pageParam) {
        Pageable pageable = PageRequest.of(pageParam.getPage(), pageParam.getSize(), new Sort(Sort.Direction.DESC, "createTime"));
        List<Byte> bytes = new ArrayList<>();
        bytes.add(ConstUtil.Order_Open);
        bytes.add(ConstUtil.Order_Open_Finish_Part);

        Page<Order> all = orderRepository.findByOrderStateIn(pageable,bytes);
        return all;
    }

    /**
     * 获取用户已完成点单
     * @param accountID
     * @param pageParam
     * @return
     */
    public Page<Order> successOrders(String accountID, PageParam pageParam) {
        Pageable pageable = PageRequest.of(pageParam.getPage(), pageParam.getSize());
        List<Byte> bytes = new ArrayList<>();
        bytes.add(ConstUtil.Order_Close);
        bytes.add(ConstUtil.Order_Close_Finish_Part);
        bytes.add(ConstUtil.Order_Close_Finish_All);
        Page<Order> all = orderRepository.findByUserIdAndOrderStateInOrderByCreateTimeDesc(pageable,accountID,bytes);
        return all;
    }
}
