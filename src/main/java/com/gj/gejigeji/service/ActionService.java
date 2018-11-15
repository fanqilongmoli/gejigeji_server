package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserLikeValue;
import com.gj.gejigeji.repository.UserLikeValueRepository;
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
    UserLikeValueRepository userLikeValueRepository;

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

        //查询好感表
        UserLikeValue userLikeValueEx = new UserLikeValue();
        userLikeValueEx.setUserId(actionParam.getAccountID());
        UserLikeValue userLikeValue = userLikeValueRepository.findOne(Example.of(userLikeValueEx)).orElse(null);
        ActionVo actionVo = new ActionVo();
        if (userLikeValue != null) {
            Date lastTime = new Date();
            userLikeValue.setStroke(userLikeValue.getStroke() + 2);
            userLikeValue.setStrokeLastTime(lastTime);
            userLikeValueRepository.save(userLikeValue);
            actionVo.setLikeValue(userLikeValue.getFeed() + userLikeValue.getStroke() + userLikeValue.getBathe() + userLikeValue.getGame() + userLikeValue.getTv());

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
        UserLikeValue userLikeValueEx = new UserLikeValue();
        userLikeValueEx.setUserId(actionParam.getAccountID());
        UserLikeValue userLikeValue = userLikeValueRepository.findOne(Example.of(userLikeValueEx)).orElse(null);
        ActionVo actionVo = new ActionVo();
        if (userLikeValue != null) {
            userLikeValue.setBathe(userLikeValue.getBathe() + 5);
            userLikeValue.setBatheLastTime(new Date());
            userLikeValueRepository.save(userLikeValue);
            actionVo.setLikeValue(userLikeValue.getFeed() + userLikeValue.getStroke() + userLikeValue.getBathe() + userLikeValue.getGame() + userLikeValue.getTv());

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
        UserLikeValue userLikeValueEx = new UserLikeValue();
        userLikeValueEx.setUserId(actionParam.getAccountID());
        UserLikeValue userLikeValue = userLikeValueRepository.findOne(Example.of(userLikeValueEx)).orElse(null);
        ActionVo actionVo = new ActionVo();
        if (userLikeValue != null) {
            userLikeValue.setTv(userLikeValue.getTv() + 1);
            userLikeValue.setTvLastTime(new Date());
            userLikeValueRepository.save(userLikeValue);
            actionVo.setLikeValue(userLikeValue.getFeed() + userLikeValue.getStroke() + userLikeValue.getBathe() + userLikeValue.getGame() + userLikeValue.getTv());

        } else {
            actionVo.setLikeValue(0);
        }


        return actionVo;
    }
}
