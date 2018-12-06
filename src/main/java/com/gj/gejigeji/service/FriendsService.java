package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.*;
import com.gj.gejigeji.model.Friends;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserEgg;
import com.gj.gejigeji.repository.FriendsRepository;
import com.gj.gejigeji.repository.UserEggRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.util.ConstUtil;
import com.gj.gejigeji.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FriendsService {

    @Autowired
    FriendsRepository friendsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserEggRepository userEggRepository;



    /**
     * 添加好友申请
     *
     * @param addFriendVo
     * @return
     */
    public OkResult addFriend(AddFriendParam addFriendVo) {

        User user = userRepository.findById(addFriendVo.getAccountID()).orElse(null);
        if (user == null) {
            throw new NoUserException();

        }

        User friend = userRepository.findByUserName(addFriendVo.getUsername()).orElse(null);
        if (friend == null) {
            throw new FriendNoException();
        }

        //检查是否存在好友关系
        Friends friends1 = friendsRepository.findByUserIdAndFriendIdOrFriendIdAndUserId(user.getId(), friend.getId(), user.getId(), friend.getId()).orElse(null);
        if (friends1 != null) {
            if (friends1.getStatus() == ConstUtil.FRIEND_OK) {
                throw new FriendHasException();
            }

            if (friends1.getStatus() == ConstUtil.FRIEND_APPLY) {
                throw new FriendApplyException();
            }

            if (friends1.getStatus() == ConstUtil.FRIEND_HIND) {
                // 更新好友列表状态
                friends1.setFriendId(friend.getId());
                friends1.setUserId(user.getId());
                friends1.setPs(addFriendVo.getPs());
                friends1.setStatus(ConstUtil.FRIEND_APPLY);
                friends1.setCreateTime(new Date());
                friends1.setUpdateTime(new Date());
                friendsRepository.save(friends1);

                return new OkResult(true);
            }

        }


        // 添加到好友表中  申请者 为userId  被申请者为 friendId
        Friends friends = new Friends();
        friends.setFriendId(friend.getId());
        friends.setUserId(user.getId());
        friends.setPs(addFriendVo.getPs());
        friends.setStatus(ConstUtil.FRIEND_APPLY);
        friends.setCreateTime(new Date());
        friends.setUpdateTime(new Date());
        friendsRepository.save(friends);

        return new OkResult(true);
    }

    /**
     * 获取好友列表
     *
     * @param accountID
     * @return
     */
    public List<FriendVo> getFriends(String accountID) {

        List<FriendVo> friendVos = new ArrayList<>();

        // 查询自己添加的好友 自己为userID
        friendsRepository.findByUserIdAndAndStatus(accountID, ConstUtil.FRIEND_OK).stream().forEach(friends -> {
            User user = userRepository.findById(friends.getFriendId()).orElse(null);
            if (user != null) {
                friendVos.add(new FriendVo(user.getId(), user.getUserName() == null ? user.getId() : user.getUserName(),friends.getPs()));
            }
        });
        // 查询对方添加的好友 自己为friendID
        friendsRepository.findByFriendIdAndAndStatus(accountID, ConstUtil.FRIEND_OK).stream().forEach(friends -> {
            User user = userRepository.findById(friends.getUserId()).orElse(null);
            if (user != null) {
                friendVos.add(new FriendVo(user.getId(), user.getUserName() == null ? user.getId() : user.getUserName(),friends.getPs()));
            }
        });


        return friendVos;
    }

    /**
     * 获取好友申请列表
     *
     * @param accountID
     * @return
     */
    public List<FriendVo> getApply(String accountID) {
        List<FriendVo> friendVos = new ArrayList<>();
        // 获取好友申请列表 自己为 friendID
        friendsRepository.findByFriendIdAndAndStatus(accountID, ConstUtil.FRIEND_APPLY).stream().forEach(friends -> {
            User user = userRepository.findById(friends.getUserId()).orElse(null);
            if (user != null) {
                friendVos.add(new FriendVo(user.getId(), user.getUserName() == null ? user.getId() : user.getUserName(),friends.getPs()));
            }
        });


        return friendVos;
    }

    /**
     * 同意好友请求/忽略好友请求
     *
     * @param applyFriendVo
     * @return
     */
    public OkResult applyFriend(ApplyFriendParam applyFriendVo) {
        // 申请好友  申请方为 userId  同意方为 friendId

        Friends friends = friendsRepository.findByUserIdAndFriendId(applyFriendVo.getFriendId(), applyFriendVo.getAccountID()).get();

        friends.setStatus(applyFriendVo.getAction());
        friends.setUpdateTime(new Date());
        friendsRepository.save(friends);
        return new OkResult(true);
    }

    /**
     * 赠送好友鸡蛋
     * @param sendEggParam
     * @return
     */
    public OkResult sendEgg(SendEggParam sendEggParam) {
        User user = userRepository.findById(sendEggParam.getAccountID()).orElse(null);
        if (user == null) {
            throw new NoUserException();

        }

        User friend = userRepository.findById(sendEggParam.getFriendID()).orElse(null);
        if (friend == null) {
            throw new FriendNoException();
        }

        //检查用户的鸡蛋数量够不够
        UserEgg userEggEx = new UserEgg();
        userEggEx.setUserId(sendEggParam.getAccountID());
        userEggEx.setFeedId(sendEggParam.getFeedId());

        UserEgg userEgg = userEggRepository.findOne(Example.of(userEggEx)).orElse(null);
        if (userEgg == null || (userEgg.getAmount() - userEgg.getFreeze()) < sendEggParam.getEgg()) {
            throw new NoEggException();
        }

        // 减少用户鸡蛋
        userEgg.setAmount(userEgg.getAmount() - sendEggParam.getEgg());
        userEggRepository.save(userEgg);


        //给被赠送者添加鸡蛋
        UserEgg userEggEx2 = new UserEgg();
        userEggEx2.setUserId(sendEggParam.getFriendID());
        userEggEx2.setFeedId(sendEggParam.getFeedId());
        UserEgg userEgg2 = userEggRepository.findOne(Example.of(userEggEx)).orElse(null);

        if (userEgg2 == null){
            UserEgg userEggNew = new UserEgg();
            userEggNew.setUserId(sendEggParam.getAccountID());
            userEggNew.setAmount(sendEggParam.getEgg());
            userEggNew.setFreeze(0);
            userEggNew.setCreateTime(new Date());
            userEggNew.setFeedId(sendEggParam.getFeedId());
            userEggNew.setUpdateTime(new Date());
            userEggNew.setDeleteFlag(ConstUtil.Delete_Flag_No);
            userEggRepository.save(userEggNew);

        }else {
            userEgg2.setAmount(userEgg.getAmount() + sendEggParam.getEgg());
            userEgg2.setUpdateTime(new Date());
            userEggRepository.save(userEgg2);
        }

        return new OkResult(true);
    }

    /**
     * 赠送好友金币
     * @param sendCoinParam
     * @return
     */
    public OkResult sendCoin(SendCoinParam sendCoinParam) {

        User user = userRepository.findById(sendCoinParam.getAccountID()).orElse(null);
        if (user == null) {
            throw new NoUserException();

        }

        User friend = userRepository.findById(sendCoinParam.getFriendID()).orElse(null);
        if (friend == null) {
            throw new FriendNoException();
        }

        //检查赠送者金币 够不够
        if (user.getCoin() < sendCoinParam.getCoin()) {
            throw new NoCoinException();
        }

        //赠送者减去金币
        user.setCoin(user.getCoin() - sendCoinParam.getCoin());
        userRepository.save(user);

        //被赠送者增加金币

        friend.setCoin(friend.getCoin()+ sendCoinParam.getCoin());
        userRepository.save(friend);

        return new OkResult(true);
    }
}
