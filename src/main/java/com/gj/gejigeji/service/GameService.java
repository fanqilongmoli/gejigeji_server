package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.exception.NoUserException;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserChicken;
import com.gj.gejigeji.repository.UserChickenRepository;
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
    UserChickenRepository UserChickenRepository;

    /**
     * 获取用户的游戏次数
     *
     * @param actionParam
     * @return
     */
    public GameCountVo gameCount(ActionParam actionParam) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new NoUserException();
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
     *
     * @param actionParam
     * @return
     */
    public GameResultVo ddsStart(ActionParam actionParam) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new NoUserException();
        }
        GameResultVo gameResultVo = new GameResultVo();
        Integer miniGameCount1 = user.getMiniGameCount1();
        // 减少游戏次数
        if (miniGameCount1 > 0) {
            user.setMiniGameCount1(miniGameCount1 - 1);
            userRepository.save(user);
            gameResultVo.setAllow(true);
            return gameResultVo;
        }
        gameResultVo.setAllow(false);
        return gameResultVo;
    }

    /**
     * 打地鼠结束
     *
     * @param ddsEndParam
     * @return
     */
    public GameResultVo ddsEnd(DDSEndParam ddsEndParam) {
        GameResultVo okResult = new GameResultVo();
        okResult.setAllow(true);
        okResult.setCoin(5);
        return okResult;
    }

    /**
     * 老虎机开始
     *
     * @param actionParam
     * @return
     */
    public GameResultVo lhjStart(ActionParam actionParam) {

        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new NoUserException();
        }
        // 减少游戏次数
        Integer miniGameCount3 = user.getMiniGameCount3();
        if (miniGameCount3 > 0) {
            user.setMiniGameCount3(miniGameCount3 - 1);
            userRepository.save(user);

            // 添加游戏的好感度
            updateGameLikeValue(actionParam.getAccountID());

            GameResultVo okResult = new GameResultVo();
            okResult.setAllow(true);
            okResult.setAwardId(111);
            return okResult;

        }
        GameResultVo gameResultVo = new GameResultVo();
        gameResultVo.setAllow(true);
        return gameResultVo;
    }

    /**
     * 大转盘开始
     *
     * @param actionParam
     * @return
     */
    public GameResultVo dzbStart(ActionParam actionParam) {

        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new NoUserException();
        }
        // 减少游戏次数
        Integer miniGameCount2 = user.getMiniGameCount2();
        if (miniGameCount2 > 0) {
            user.setMiniGameCount2(miniGameCount2 - 1);
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
     *
     * @param actionParam
     * @return
     */
    public GameResultVo dgsStart(ActionParam actionParam) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new NoUserException();
        }
        GameResultVo gameResultVo = new GameResultVo();
        Integer miniGameCount4 = user.getMiniGameCount4();
        if (miniGameCount4 > 0) {
            user.setMiniGameCount4(miniGameCount4 - 1);
            userRepository.save(user);
            gameResultVo.setAllow(true);
            return gameResultVo;

        }
        gameResultVo.setAllow(false);
        return gameResultVo;
    }

    /**
     * 打怪兽结束
     *
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
     *
     * @param accountID
     */
    private void updateGameLikeValue(String accountID) {
        UserChicken UserChickenEx = new UserChicken();
        UserChickenEx.setUserId(accountID);
        UserChicken UserChicken = UserChickenRepository.findOne(Example.of(UserChickenEx)).orElse(null);
        ActionVo actionVo = new ActionVo();
        if (UserChicken != null) {
            Date lastTime = new Date();
            UserChicken.setStroke(UserChicken.getGame() + 3);
            UserChicken.setGameLastTime(lastTime);
            UserChickenRepository.save(UserChicken);
            actionVo.setLikeValue(UserChicken.getFeed() + UserChicken.getStroke() + UserChicken.getBathe() + UserChicken.getGame() + UserChicken.getTv());

        }
    }

    /**
     * 每个游戏每天各有3次免费的机会，超过免费机会，每次10个金币
     *
     * @param actionParam
     * @param game        游戏类型 1打地鼠 2大转盘 3老虎机 4打怪兽
     * @return
     */
    public OkResult checkGame4Free(ActionParam actionParam, String game) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new NoUserException();
        }
        switch (game) {
            case "1":
                if (user.getMiniGameCount1() > 7) {
                    return new OkResult(true);
                } else {
                    return new OkResult(false);
                }
            case "2":
                if (user.getMiniGameCount2() > 7) {
                    return new OkResult(true);
                } else {
                    return new OkResult(false);
                }
            case "3":
                if (user.getMiniGameCount3() > 7) {
                    return new OkResult(true);
                } else {
                    return new OkResult(false);
                }
            case "4":
                if (user.getMiniGameCount4() > 7) {
                    return new OkResult(true);
                } else {
                    return new OkResult(false);
                }
        }
        return new OkResult(false);
    }
}
