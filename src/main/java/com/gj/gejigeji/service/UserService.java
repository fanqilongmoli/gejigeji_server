package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserSite;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.repository.UserSiteRepository;
import com.gj.gejigeji.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSiteRepository userSiteRepository;

    /**
     * 获取金币数量
     * @param actionParam
     * @return
     */
    public GetCoinVo getCoin(ActionParam actionParam) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null){
            throw new BaseRuntimeException("login.user.null");
        }
        GetCoinVo getCoinVo = new GetCoinVo();
        getCoinVo.setCoin(user.getCoin());

        return getCoinVo;
    }

    /**
     * 修改昵称
     * @param updateUserNameParam
     * @return
     */
    public OkResult updateUserName(UpdateUserNameParam updateUserNameParam) {
        User userEx = new User();
        userEx.setId(updateUserNameParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null){
            throw new BaseRuntimeException("login.user.null");
        }

        user.setUserName(updateUserNameParam.getUserName());
        userRepository.save(user);

        return new OkResult(true);
    }

    /**
     * 修改配送信息
     * @param updateSiteParam
     * @return
     */
    public OkResult updateSite(UpdateSiteParam updateSiteParam) {

        UserSite userSiteEx = new UserSite();
        userSiteEx.setUserId(updateSiteParam.getAccountID());

        UserSite userSite = userSiteRepository.findOne(Example.of(userSiteEx)).orElse(new UserSite());
        userSite.setPhone(updateSiteParam.getPhone());
        userSite.setUserId(updateSiteParam.getAccountID());
        userSite.setSite(updateSiteParam.getSite());
        userSite.setUserName(updateSiteParam.getUserName());
        userSiteRepository.save(userSite);
        return new OkResult(true);

    }

    /**
     * 修改密码
     * @param updatePasswordParam
     * @return
     */
    public OkResult updatePassword(UpdatePasswordParam updatePasswordParam) {
        User userEx = new User();
        userEx.setId(updatePasswordParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null){
            throw new BaseRuntimeException("login.user.null");
        }

        if (user.getPassword().equals(updatePasswordParam.getFormerPas())) {
            user.setPassword(updatePasswordParam.getNewPas());
            userRepository.save(user);

            return new OkResult(true);

        }else {
            return new OkResult(false);
        }

    }

    /**
     * 用户认证
     * @param authenticationParam
     * @return
     */
    public OkResult authentication(AuthenticationParam authenticationParam) {
        // TODO: 2018/11/14  用户认证怎么搞
        return new OkResult(true);
    }
}
