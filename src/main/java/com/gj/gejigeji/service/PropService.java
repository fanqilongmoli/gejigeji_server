package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.*;
import com.gj.gejigeji.repository.PropRepository;
import com.gj.gejigeji.repository.UserPropRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.vo.ActionParam;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.PropBuyParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PropService {

    @Autowired
    UserPropRepository userPropRepository;

    @Autowired
    PropRepository propRepository;

    @Autowired
    UserRepository userRepository;




    public OkResult propTest(String userId) {

        return new OkResult(true);
    }

    public List<Prop> getAll(ActionParam actionParam) {
        // 获取所有道具
        List<Prop> allProp = propRepository.findAll();
        for (Prop prop : allProp) {
            prop.setHave(false);
        }

        // 判断出已拥有的道具
        UserProp userPropEx = new UserProp();
        userPropEx.setUserId(actionParam.getAccountID());
        List<UserProp> UserProps = userPropRepository.findAll(Example.of(userPropEx));
        for (UserProp userProp : UserProps) {
            for (Prop prop : allProp) {
                if (prop.getId().equals(userProp.getPropId())) {
                    prop.setHave(true);
                }
            }
        }
        
        return allProp;
    }

    public OkResult buy(PropBuyParam propBuyParam) {

        User userEx = new User();
        userEx.setId(propBuyParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null){
            throw new BaseRuntimeException("login.user.null");
        }

        // 检查是否已经购买过道具
        UserProp userPropEx = new UserProp();
        userPropEx.setUserId(propBuyParam.getAccountID());
        userPropEx.setPropId(propBuyParam.getPropId());
        UserProp userProp = userPropRepository.findOne(Example.of(userPropEx)).orElse(null);
        if (userProp != null){
            throw new BaseRuntimeException("has.prop");
        }

        // 检查主题是否存在
        Prop propEx = new Prop();
        propEx.setId(propBuyParam.getPropId());
        Prop prop = propRepository.findOne(Example.of(propEx)).orElse(null);

        if (prop==null){
            throw new BaseRuntimeException("no.prop");
        }


        // 检查金币是否充足
        Float resultCoin = user.getCoin() - prop.getPrice();
        if (resultCoin<0){
            throw new BaseRuntimeException("no.money");

        }
        // 更新用户金币数
        user.setCoin(resultCoin);
        userRepository.save(user);

        //保存用户的主题
        userProp = new UserProp();
        userProp.setUserId(propBuyParam.getAccountID());
        userProp.setPropId(propBuyParam.getPropId());

        userPropRepository.save(userProp);

        return new OkResult(true);
    }
}
