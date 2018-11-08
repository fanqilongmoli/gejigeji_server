package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    UserRepository userRepository;


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

    public OkResult ddsStart(ActionParam actionParam) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        return new OkResult(true);
    }

    public OkResult ddsEnd(DDSEndParam ddsEndParam) {
        OkResult okResult = new OkResult(true);
        okResult.setCoin(5);
        return okResult;
    }

    public OkResult lhjStart(ActionParam actionParam) {
        OkResult okResult = new OkResult(true);
        okResult.setAwardId("23123123");
        return okResult;
    }

    public GameResultVo dzbStart(ActionParam actionParam) {
        GameResultVo gameResultVo = new GameResultVo();
        gameResultVo.setAllow(true);
        gameResultVo.setAllowID(12312);
        gameResultVo.setCount(5);
        return gameResultVo;
    }

    public OkResult dgsStart(ActionParam actionParam) {
        return new OkResult(true);
    }

    public GameResultVo dgsEnd(DGSEndParam dgsEndParam) {
        GameResultVo gameResultVo = new GameResultVo();
        gameResultVo.setAllow(true);
        gameResultVo.setCount(123);
        return gameResultVo;
    }
}
