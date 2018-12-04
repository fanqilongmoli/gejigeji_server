package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.*;
import com.gj.gejigeji.repository.*;
import com.gj.gejigeji.util.ConstUtil;
import com.gj.gejigeji.vo.*;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSiteRepository userSiteRepository;

    @Autowired
    UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    UserChickenRepository userChickenRepository;

    @Autowired
    UserEggRepository userEggRepository;

    @Autowired
    UserFeedRecordRepository userFeedRecordRepository;

    @Autowired
    ChickenRepository chickenRepository;

    @Autowired
    FeedRepository feedRepository;

    /**
     * 获取金币数量  随机添加用户的金币数
     *
     * @param actionParam
     * @return
     */
    public GetCoinVo getCoin(ActionParam actionParam) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }
        GetCoinVo getCoinVo = new GetCoinVo();
        getCoinVo.setCoin(user.getCoin());

        return getCoinVo;
    }

    /**
     * 修改昵称
     *
     * @param updateUserNameParam
     * @return
     */
    public OkResult updateUserName(UpdateUserNameParam updateUserNameParam) {
        User userEx = new User();
        userEx.setId(updateUserNameParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        user.setUserName(updateUserNameParam.getUserName());
        userRepository.save(user);

        return new OkResult(true);
    }

    /**
     * 修改配送信息
     *
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
     *
     * @param updatePasswordParam
     * @return
     */
    public OkResult updatePassword(UpdatePasswordParam updatePasswordParam) {
        User userEx = new User();
        userEx.setId(updatePasswordParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        if (user.getPassword().equals(updatePasswordParam.getFormerPas())) {
            user.setPassword(updatePasswordParam.getNewPas());
            userRepository.save(user);

            return new OkResult(true);

        } else {
            return new OkResult(false);
        }

    }

    /**
     * 用户认证
     *
     * @param authenticationParam
     * @return
     */
    public OkResult authentication(AuthenticationParam authenticationParam) {

        UserAuthentication userAuthenticationEx = new UserAuthentication();
        userAuthenticationEx.setUserId(authenticationParam.getAccountID());
        UserAuthentication userAuthentication = userAuthenticationRepository.findOne(Example.of(userAuthenticationEx)).orElse(new UserAuthentication());
        userAuthentication.setUserId(authenticationParam.getAccountID());
        userAuthentication.setUserName(authenticationParam.getUserName());
        userAuthentication.setPapers(authenticationParam.getPapers());
        userAuthentication.setOk(true); //暂时默认为true
        userAuthenticationRepository.save(userAuthentication);

        return new OkResult(true);
    }

    /**
     * 下蛋请求
     *
     * @param actionParam
     * @return
     */
    public EggCountVo egg(ActionParam actionParam) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        //演示版 默认只有一只鸡  正式的应该加上 鸡的id

        //检查是否可以下蛋
        UserChicken userChickenEx = new UserChicken();
        userChickenEx.setUserId(actionParam.getAccountID());
        UserChicken userChicken = userChickenRepository.findAll(Example.of(userChickenEx)).get(0);
        Integer maxEgg = chickenRepository.findAll().get(0).getMaxEgg();
        Integer dayEgg = userChicken.getDayEgg();
        if (dayEgg >= maxEgg) {
            // TODO: 2018/11/18 抛出一个不能下蛋的异常 好像么有必要
        }

        //查询喂食记录表  获取最后一次喂食的饲料
        UserFeedRecord userFeedRecordEx = new UserFeedRecord();
        userFeedRecordEx.setUserId(actionParam.getAccountID());
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(0, 1, sort);
        List<UserFeedRecord> content = userFeedRecordRepository.findAll(pageable).getContent();
        UserFeedRecord userFeedRecord = content.get(0);

        //保存用户下蛋的和什么饲料相关的蛋
        UserEgg userEggEx = new UserEgg();
        userEggEx.setFeedId(userFeedRecord.getFeedId());
        userEggEx.setUserId(actionParam.getAccountID());
        UserEgg userEgg = userEggRepository.findOne(Example.of(userEggEx)).orElse(null);
        if (userEgg == null) {
            userEgg = new UserEgg();
            userEgg.setUserId(actionParam.getAccountID());
            userEgg.setAmount(1);
            userEgg.setFreeze(0);
            userEgg.setCreateTime(new Date());
            userEgg.setFeedId(userFeedRecord.getFeedId());
            userEgg.setUpdateTime(new Date());
            userEgg.setDeleteFlag(ConstUtil.Delete_Flag_No);
        }
        userEgg.setUpdateTime(new Date());
        userEgg.setAmount(userEgg.getAmount() + 1);
        UserEgg save = userEggRepository.save(userEgg);

        //更新UserChicken 表
        userChicken.setDayEgg(userChicken.getDayEgg() + 1);
        userChickenRepository.save(userChicken);


        EggCountVo eggCountVo = new EggCountVo();
        eggCountVo.setEggCount(save.getAmount());

        return eggCountVo;
    }

    /**
     * 获取用户的鸡蛋
     *
     * @param actionParam
     * @return
     */
    public List<UserEggVo> userEggs(ActionParam actionParam) {

        List<UserEggVo> userEggVos = new ArrayList<>();

        UserEgg userEggEx = new UserEgg();
        userEggEx.setUserId(actionParam.getAccountID());
        List<UserEgg> all = userEggRepository.findAll(Example.of(userEggEx));
        UserEggVo userEggVo;
        for (UserEgg userEgg : all) {
            userEggVo = new UserEggVo();
            userEggVo.setUserEgg(userEgg);

            Feed feedEx = new Feed();
            feedEx.setId(userEgg.getFeedId());
            Feed feed = feedRepository.findOne(Example.of(feedEx)).orElse(null);
            if (feed != null) {
                userEggVo.setEggName(feed.getEggName());
            }
            userEggVos.add(userEggVo);

        }
        return userEggVos;
    }

    /**
     * 用户获取金币--->随机添加  1-3
     * @param actionParam
     * @return
     */
    public GetCoinVo addCoin(ActionParam actionParam) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }
        // 随机为用户添加 [1,3] 个金币
        int i = RandomUtils.nextInt(1, 4);
        user.setCoin(user.getCoin()+i);
        User save = userRepository.save(user);

        GetCoinVo getCoinVo = new GetCoinVo();
        getCoinVo.setCoin(save.getCoin());
        return getCoinVo;
    }
}
