package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.SwitchSkinParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


@Service
public class SwitchSkinService {

    @Autowired
    UserRepository userRepository;

    public OkResult switchSkin(SwitchSkinParam switchSkinParam) {

        User ex = new User();
        ex.setId(switchSkinParam.getAccountID());
        User user = userRepository.findOne(Example.of(ex)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        } else {
            user.setSkinID(switchSkinParam.getItemID());
            userRepository.save(user);
            return new OkResult(true);
        }

    }
}
