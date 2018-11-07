package com.gj.gejigeji.service;

import com.gj.gejigeji.model.Prop;
import com.gj.gejigeji.model.UserProp;
import com.gj.gejigeji.repository.PropRepository;
import com.gj.gejigeji.repository.UserPropRepository;
import com.gj.gejigeji.vo.OkResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropService {

    @Autowired
    UserPropRepository userPropRepository;

    @Autowired
    PropRepository propRepository;


    public OkResult propTest(String userId) {
        List<Prop> all = propRepository.findAll();
        for (Prop prop : all) {

            UserProp probe = new UserProp();
            probe.setUserId(userId);
            probe.setItemId(prop.getId());
            UserProp userProp1 = userPropRepository.findOne(Example.of(probe)).orElse(null);
            if (userProp1!=null){
                userProp1.setItemCount(10);
                userPropRepository.save(userProp1);
            }else {
                UserProp userProp = new UserProp();
                userProp.setUserId(userId);
                userProp.setItemId(prop.getId());
                userProp.setItemCount(10);
                userPropRepository.save(userProp);
            }

        }

        return new OkResult(true);
    }
}
