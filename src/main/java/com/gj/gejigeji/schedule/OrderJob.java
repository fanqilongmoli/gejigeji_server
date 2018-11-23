package com.gj.gejigeji.schedule;

import com.gj.gejigeji.model.*;
import com.gj.gejigeji.repository.*;
import com.gj.gejigeji.util.ConstUtil;
import org.apache.commons.lang3.time.DateUtils;
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
public class OrderJob {

    private static final Logger logger = LoggerFactory.getLogger(OrderJob.class);


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RecycleRecordRepository recycleRecordRepository;

    @Autowired
    UserEggRepository userEggRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuctionChartRepository auctionChartRepository;

    @Transactional
    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void recycle() {
        logger.warn("=============检查是否可以回收==================");
        RecycleRecord recycleRecord = recycleRecordRepository.findTopByOrderByCreateTimeDesc().orElse(null);
        if (recycleRecord == null) {
            recycleRecord = new RecycleRecord();
            recycleRecord.setCreateTime(new Date());
            recycleRecord.setPrice(0);
            recycleRecord.setVol(0);
            recycleRecord.setUserId("-1");
            recycleRecordRepository.save(recycleRecord);
        } else {
            if (Math.abs(UserJobs.calculateTimeDifferenceByCalendar(recycleRecord.getCreateTime(), Calendar.HOUR)) >= 8) {
                startRecycle();
            }


        }

    }

    /**
     * 开始回收 每次回收50个
     */
    @Transactional
    public void startRecycle() {

        // 查询出所有的挂单
        List<Order> orders = orderRepository.findAllByOrderByPriceAsc();
        logger.warn("查询到的当前的挂单的数量=====" + orders.size());

        //可回收数量
        int allAmount = 50;
        for (Order order : orders) {
            //检查是否回收数量够了
            //if (allAmount <= 0) {
            //    break;
            //}

            //当前订单 需要回收的交易量
            int vol = order.getAmount() - order.getVolume();

            // 可回收数量 大于 需要回收的数量
            if (allAmount >= vol) {
                allAmount = allAmount - vol;
                // 更新用户-鸡蛋 信息 和 用户的金币数量
                updateUserEgg(order, vol);
                // 更新订单信息 全部完成
                order.setOrderState(ConstUtil.Order_Close_Finish_All);
                order.setVolume(vol);
                orderRepository.save(order);
                // 添加回收记录
                addRecord(order, vol);

            } else {
                // 可回收数量 小于 需要回收的数量
                // 可回收的数量
                int realVol = vol - allAmount;

                // 更新用户-鸡蛋 信息 和 用户的金币数量
                updateUserEgg(order, realVol);
                // 更新订单信息 部分完成
                order.setOrderState(ConstUtil.Order_Open_Finish_Part);
                order.setVolume(realVol);
                orderRepository.save(order);
                // 添加回收记录
                addRecord(order, realVol);

                break;
            }


        }
    }

    /**
     * 更新用户-鸡蛋 信息 和 用户的金币数量
     *
     * @param order 订单
     * @param vol   成交数量
     */
    private void updateUserEgg(Order order, Integer vol) {
        UserEgg userEgg = userEggRepository.findUserEggByUserIdAndFeedId(order.getUserId(), order.getFeedId()).get();
        userEgg.setFreeze(userEgg.getFreeze() - vol);
        userEgg.setAmount(userEgg.getAmount() - vol);
        userEggRepository.save(userEgg);

        User user = userRepository.findById(order.getUserId()).get();
        int addCoin = vol * order.getPrice();
        user.setCoin(user.getCoin() + addCoin);
        userRepository.save(user);
    }


    /**
     * 添加回收记录 同时添加到回收的图表中
     *
     * @param order 订单
     * @param vol   成交数量
     */
    private void addRecord(Order order, Integer vol) {
        RecycleRecord recycleRecord = new RecycleRecord();
        recycleRecord.setUserId(order.getUserId());
        recycleRecord.setVol(vol);
        recycleRecord.setCreateTime(new Date());
        recycleRecord.setPrice(order.getPrice());
        recycleRecordRepository.save(recycleRecord);

        AuctionChart auctionChart = auctionChartRepository.findFirstByOrderByDateDesc().orElse(null);

        if (auctionChart != null && DateUtils.isSameDay(auctionChart.getDate(), new Date())){
            //不为空 是同一天  更新当前记录

            //更新当前记录
            // v
            auctionChart.setVol(auctionChart.getVol()+vol);
            // h
            if (order.getPrice()>auctionChart.getHigh()) {
                auctionChart.setHigh(order.getPrice());
            }
            // l
            if (order.getPrice()<auctionChart.getLow()) {
                auctionChart.setLow(order.getPrice());
            }

            // o
            //auctionChart.setOpen(order.getPrice());
            // c
            auctionChart.setClose(order.getPrice());
            // d
            //
            // auctionChart.setDate(new Date());

        }else {
            //添加新的记录
            auctionChart = new AuctionChart();
            // v
            auctionChart.setVol(auctionChart.getVol()+vol);
            // h
            auctionChart.setHigh(order.getPrice());
            // l
            auctionChart.setLow(order.getPrice());
            // o
            auctionChart.setOpen(order.getPrice());
            // c
            auctionChart.setClose(order.getPrice());
            // d
            auctionChart.setDate(new Date());
        }
        auctionChartRepository.save(auctionChart);

    }
}
