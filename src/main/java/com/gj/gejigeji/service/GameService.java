package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserLikeValue;
import com.gj.gejigeji.repository.UserLikeValueRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GameService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserLikeValueRepository userLikeValueRepository;


    public GameCountVo gameCount(ActionParam actionParam) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        GameCountVo gameCountVo = new GameCountVo();
        gameCountVo.setMiniGameCount1(user.getMiniGameCount1());
        gameCountVo.setMiniGameCount2(user.getMiniGameCount2());
        gameCountVo.setMiniGameCount3(user.getMiniGameCount3());
        gameCountVo.setMiniGameCount4(user.getMiniGameCount4());

        return gameCountVo;
    }

    /**
     * 打地鼠开始
     * @param actionParam
     * @return
     */
    public OkResult ddsStart(ActionParam actionParam) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        Integer miniGameCount1 = user.getMiniGameCount1();
        if (miniGameCount1>0){
            user.setMiniGameCount1(miniGameCount1-1);
            userRepository.save(user);

            // 添加游戏的好感度
            updateGameLikeValue(actionParam.getAccountID());

            return new OkResult(true);
        }
        return new OkResult(false);
    }

    /**
     * 打地鼠结束
     * @param ddsEndParam
     * @return
     */
    public OkResult ddsEnd(DDSEndParam ddsEndParam) {
        OkResult okResult = new OkResult(true);
        okResult.setCoin(5);
        return okResult;
    }

    /**
     * 老虎机开始
     * @param actionParam
     * @return
     */
    public OkResult lhjStart(ActionParam actionParam) {

        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        Integer miniGameCount3 = user.getMiniGameCount3();
        if (miniGameCount3>0){
            user.setMiniGameCount3(miniGameCount3-1);
            userRepository.save(user);

            // 添加游戏的好感度
            updateGameLikeValue(actionParam.getAccountID());

            OkResult okResult = new OkResult(true);
            okResult.setAwardId("23123123");
            return okResult;

        }
        return new OkResult(false);
    }

    /**
     * 大转盘开始
     * @param actionParam
     * @return
     */
    public GameResultVo dzbStart(ActionParam actionParam) {

        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        Integer miniGameCount2 = user.getMiniGameCount2();
        if (miniGameCount2>0){
            user.setMiniGameCount2(miniGameCount2-1);
            userRepository.save(user);

            // 添加游戏的好感度
            updateGameLikeValue(actionParam.getAccountID());

            GameResultVo gameResultVo = new GameResultVo();
            gameResultVo.setAllow(true);
            gameResultVo.setAllowID(12312);
            gameResultVo.setCount(5);
            return gameResultVo;
        }

        GameResultVo gameResultVo = new GameResultVo();
        gameResultVo.setAllow(false);
        return gameResultVo;
    }

    /**
     * 打怪兽开始
     * @param actionParam
     * @return
     */
    public OkResult dgsStart(ActionParam actionParam) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        Integer miniGameCount4 = user.getMiniGameCount4();
        if (miniGameCount4>0){
            user.setMiniGameCount4(miniGameCount4-1);
            userRepository.save(user);

            // 添加游戏的好感度
            updateGameLikeValue(actionParam.getAccountID());

            return new OkResult(true);

        }
        return new OkResult(false);
    }

    /**
     * 打怪兽结束
     * @param dgsEndParam
     * @return
     */
    public GameResultVo dgsEnd(DGSEndParam dgsEndParam) {
        GameResultVo gameResultVo = new GameResultVo();
        gameResultVo.setAllow(true);
        gameResultVo.setCount(123);
        return gameResultVo;
    }


    /**
     * 跟新好感度
     * @param accountID
     */
    private void updateGameLikeValue(String accountID){
        UserLikeValue userLikeValueEx = new UserLikeValue();
        userLikeValueEx.setUserId(accountID);
        UserLikeValue userLikeValue = userLikeValueRepository.findOne(Example.of(userLikeValueEx)).orElse(null);
        ActionVo actionVo = new ActionVo();
        if (userLikeValue != null) {
            Date lastTime = new Date();
            userLikeValue.setStroke(userLikeValue.getGame() + 3);
            userLikeValue.setGameLastTime(lastTime);
            userLikeValueRepository.save(userLikeValue);
            actionVo.setLikeValue(userLikeValue.getFeed() + userLikeValue.getStroke() + userLikeValue.getBathe() + userLikeValue.getGame() + userLikeValue.getTv());

        }
    }
}
