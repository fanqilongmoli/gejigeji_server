package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserChicken;
import com.gj.gejigeji.model.UserChicken;
import com.gj.gejigeji.repository.ChickenRepository;
import com.gj.gejigeji.repository.UserChickenRepository;
import com.gj.gejigeji.repository.UserChickenRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.vo.ActionParam;
import com.gj.gejigeji.vo.ActionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ActionService {

    /**
     * 后台逻辑和接口服务。亲密值总共100点。
     * 分配情况：抚摸10点，喂食30点，洗澡10点，玩耍45点，看电视5点。
     * 亲密度增加逻辑：抚摸一次2点，喂食一次10点，洗澡一次5点，玩耍一次3点，看电视一次1点。
     * 亲密度衰减逻辑：均以最近一次操作时间为基准，抚摸，每1小时衰减1点，喂食，每小时衰减2点，洗澡每小时衰减1点，玩耍每小时3点，看电视每小时1点。
     */

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserChickenRepository userChickenRepository;

    @Autowired
    ChickenRepository chickenRepository;

    /**
     * 抚摸
     *
     * @param actionParam
     * @return
     */
    public ActionVo stroke(ActionParam actionParam) {

        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        //查询好感
        UserChicken userChickenEx = new UserChicken();
        userChickenEx.setUserId(actionParam.getAccountID());
        UserChicken userChicken = userChickenRepository.findOne(Example.of(userChickenEx)).orElse(null);
        ActionVo actionVo = new ActionVo();
        if (userChicken != null) {
            Date lastTime = new Date();
            userChicken.setStroke(userChicken.getStroke() + 2);
            userChicken.setStrokeLastTime(lastTime);

            //检查是否可以下蛋
            Integer maxEgg = chickenRepository.findAll().get(0).getMaxEgg();
            Integer dayEgg = userChicken.getDayEgg();
            int likeValue = userChicken.getFeed() + userChicken.getStroke() + userChicken.getBathe() + userChicken.getGame() + userChicken.getTv();
            if (dayEgg < maxEgg && likeValue == 100) {
                actionVo.setEgg(true);
                userChicken.setDayEgg(dayEgg + 1);
            }
            userChickenRepository.save(userChicken);
            actionVo.setLikeValue(likeValue);


        } else {
            actionVo.setLikeValue(0);
        }


        return actionVo;


    }

    /**
     * 洗澡
     *
     * @param actionParam
     * @return
     */
    public ActionVo bathe(ActionParam actionParam) {

        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        //查询好感表
        UserChicken userChickenEx = new UserChicken();
        userChickenEx.setUserId(actionParam.getAccountID());
        UserChicken userChicken = userChickenRepository.findOne(Example.of(userChickenEx)).orElse(null);
        ActionVo actionVo = new ActionVo();
        if (userChicken != null) {

            userChicken.setBathe(userChicken.getBathe() + 5);
            userChicken.setBatheLastTime(new Date());

            //检查是否可以下蛋
            Integer maxEgg = chickenRepository.findAll().get(0).getMaxEgg();
            Integer dayEgg = userChicken.getDayEgg();
            int likeValue = userChicken.getFeed() + userChicken.getStroke() + userChicken.getBathe() + userChicken.getGame() + userChicken.getTv();
            if (dayEgg < maxEgg && likeValue == 100) {
                actionVo.setEgg(true);
                userChicken.setDayEgg(dayEgg + 1);
            }
            userChickenRepository.save(userChicken);
            actionVo.setLikeValue(likeValue);

        } else {
            actionVo.setLikeValue(0);
        }


        return actionVo;
    }

    /**
     * 打扫
     *
     * @param actionParam
     * @return
     */
    public ActionVo clean(ActionParam actionParam) {


        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        //查询好感表
        UserChicken userChickenEx = new UserChicken();
        userChickenEx.setUserId(actionParam.getAccountID());
        UserChicken userChicken = userChickenRepository.findOne(Example.of(userChickenEx)).orElse(null);
        ActionVo actionVo = new ActionVo();
        if (userChicken != null) {
            userChicken.setTv(userChicken.getTv() + 1);
            userChicken.setTvLastTime(new Date());
            //检查是否可以下蛋
            Integer maxEgg = chickenRepository.findAll().get(0).getMaxEgg();
            Integer dayEgg = userChicken.getDayEgg();
            int likeValue = userChicken.getFeed() + userChicken.getStroke() + userChicken.getBathe() + userChicken.getGame() + userChicken.getTv();
            if (dayEgg < maxEgg && likeValue == 100) {
                actionVo.setEgg(true);
                userChicken.setDayEgg(dayEgg + 1);
            }
            userChickenRepository.save(userChicken);
            actionVo.setLikeValue(likeValue);

        } else {
            actionVo.setLikeValue(0);
        }


        return actionVo;
    }
}
