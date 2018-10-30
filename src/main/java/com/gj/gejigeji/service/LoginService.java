package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.vo.BindPhoneParam;
import com.gj.gejigeji.vo.Login3rdParam;
import com.gj.gejigeji.vo.LoginParam;
import com.gj.gejigeji.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public LoginVo login(LoginParam loginParam) {
        User ex = new User();
        ex.setPhone(loginParam.getPhone());

        User user = userRepository.findOne(Example.of(ex)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        return new LoginVo(user.getId(), user.getEggCount(), user.getCoin(), user.getJewel(), user.getLikeValue(), user.getAward(), user.getSkinID(), null, false);
    }

    public LoginVo login3rd(Login3rdParam login3rdParam) {
        User ex = new User();
        ex.setPhone(login3rdParam.getPlatformID());

        User user = userRepository.findOne(Example.of(ex)).orElse(null);


        if (user == null) {
            LoginVo loginVo = new LoginVo();
            loginVo.setBindPhone(true);
            return loginVo;

        } else {
            return new LoginVo(user.getId(), user.getEggCount(), user.getCoin(), user.getJewel(), user.getLikeValue(), user.getAward(), user.getSkinID(), null, false);

        }

    }

    public LoginVo bindPhone(BindPhoneParam bindPhoneParam) {
        User user = new User();
        user.setPlatformID(bindPhoneParam.getPlatformID());
        user.setPhone(bindPhoneParam.getPhone());
        User save = userRepository.save(user);
        return new LoginVo(save.getId(), save.getEggCount(), save.getCoin(), save.getJewel(), save.getLikeValue(), save.getAward(), save.getSkinID(), null, false);
    }
}
