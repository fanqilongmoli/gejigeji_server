package com.gj.gejigeji.service;

import com.gj.gejigeji.chat.listener.PublishService;
import com.gj.gejigeji.exception.*;
import com.gj.gejigeji.model.Friends;
import com.gj.gejigeji.model.Message;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserEgg;
import com.gj.gejigeji.repository.FriendsRepository;
import com.gj.gejigeji.repository.MessageRepository;
import com.gj.gejigeji.repository.UserEggRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.util.ConstUtil;
import com.gj.gejigeji.util.JsonUtil;
import com.gj.gejigeji.vo.*;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class FriendsService {

    @Autowired
    FriendsRepository friendsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserEggRepository userEggRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    PublishService publishService;


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
                // 检查是否有唯独消息
                List<Message> unReadMessages = messageRepository.findByFromAndToAndStatus(friends.getFriendId(), accountID, ConstUtil.MSG_UNREAD);

                friendVos.add(new FriendVo(user.getId(), user.getUserName() == null ? user.getId() : user.getUserName(), friends.getPs(), friends.getLastMsgTime(), unReadMessages.size() > 0));
            }
        });
        // 查询对方添加的好友 自己为friendID
        friendsRepository.findByFriendIdAndAndStatus(accountID, ConstUtil.FRIEND_OK).stream().forEach(friends -> {
            User user = userRepository.findById(friends.getUserId()).orElse(null);
            if (user != null) {
                // 检查是否有唯独消息
                List<Message> unReadMessages = messageRepository.findByFromAndToAndStatus(friends.getUserId(), accountID, ConstUtil.MSG_UNREAD);
                friendVos.add(new FriendVo(user.getId(), user.getUserName() == null ? user.getId() : user.getUserName(), friends.getPs(), friends.getLastMsgTime(), unReadMessages.size() > 0));
            }
        });

        // 排序 根据最后聊天时间
        friendVos.sort((o1, o2) -> DateUtils.truncatedCompareTo(o1.getLastMsgTime(), o2.getLastMsgTime(), Calendar.MINUTE));

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
                friendVos.add(new FriendVo(user.getId(), user.getUserName() == null ? user.getId() : user.getUserName(), friends.getPs()));
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

        Friends friends = friendsRepository.findByUserIdAndFriendId(applyFriendVo.getFriendId(), applyFriendVo.getAccountID()).orElse(null);
        if (friends != null) {
            friends.setStatus(applyFriendVo.getAction());
            friends.setUpdateTime(new Date());
            friends.setLastMsgTime(new Date());
            friendsRepository.save(friends);
            return new OkResult(true);
        }

        throw new NoUserException();

    }

    /**
     * 赠送好友鸡蛋
     *
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

        if (userEgg2 == null) {
            UserEgg userEggNew = new UserEgg();
            userEggNew.setUserId(sendEggParam.getAccountID());
            userEggNew.setAmount(sendEggParam.getEgg());
            userEggNew.setFreeze(0);
            userEggNew.setCreateTime(new Date());
            userEggNew.setFeedId(sendEggParam.getFeedId());
            userEggNew.setUpdateTime(new Date());
            userEggNew.setDeleteFlag(ConstUtil.Delete_Flag_No);
            userEggRepository.save(userEggNew);

        } else {
            userEgg2.setAmount(userEgg.getAmount() + sendEggParam.getEgg());
            userEgg2.setUpdateTime(new Date());
            userEggRepository.save(userEgg2);
        }

        //同时给双方发送消息

        try {
            // 发送给赠送者
            Message message = new Message();
            message.setFrom("sys" + friend.getId());
            message.setTo(user.getId());
            message.setContent(String.format(ConstUtil.EGG_SEND_TIP, friend.getUserName(), sendEggParam.getEgg()));
            message.setMsgType(ConstUtil.MSG_TYPE_SYS);
            message.setCreateTime(new Date());
            publishService.publish(user.getId(), JsonUtil.serialize(message));
            // 发送给接受者
            Message message2 = new Message();
            message2.setFrom("sys" + user.getId());
            message2.setTo(friend.getId());
            message2.setContent(String.format(ConstUtil.EGG_RECEIVE_TIP, user.getUserName(), sendEggParam.getEgg()));
            message2.setMsgType(ConstUtil.MSG_TYPE_SYS);
            message2.setCreateTime(new Date());
            publishService.publish(friend.getId(), JsonUtil.serialize(message2));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new OkResult(true);
    }

    /**
     * 赠送好友金币
     *
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

        friend.setCoin(friend.getCoin() + sendCoinParam.getCoin());
        userRepository.save(friend);

        //同时给双方发送消息

        try {
            // 发送给赠送者
            Message message = new Message();
            message.setFrom("sys" + friend.getId());
            message.setTo(user.getId());
            message.setContent(String.format(ConstUtil.COIN_SEND_TIP, friend.getUserName(), sendCoinParam.getCoin()));
            message.setMsgType(ConstUtil.MSG_TYPE_SYS);
            message.setCreateTime(new Date());
            publishService.publish(user.getId(), JsonUtil.serialize(message));
            // 发送给接受者
            Message message2 = new Message();
            message2.setFrom("sys" + user.getId());
            message2.setTo(friend.getId());
            message2.setContent(String.format(ConstUtil.COIN_RECEIVE_TIP, user.getUserName(), sendCoinParam.getCoin()));
            message2.setMsgType(ConstUtil.MSG_TYPE_SYS);
            message2.setCreateTime(new Date());
            publishService.publish(friend.getId(), JsonUtil.serialize(message2));

        } catch (IOException e) {
            e.printStackTrace();
        }


        return new OkResult(true);
    }

    /**
     * 获取聊天记录
     *
     * @param getMessagesParam
     */
    public List<MessageHisVo> getMessages(GetMessagesParam getMessagesParam) {
        User user = userRepository.findById(getMessagesParam.getAccountID()).get();
        User friend = userRepository.findById(getMessagesParam.getFriendID()).get();

        Sort sort = new Sort(Sort.Direction.DESC, "createTime"); //创建时间降序排序
        Pageable pageable = PageRequest.of(getMessagesParam.getPage(), getMessagesParam.getSize(), sort);


        final List<Message> messages = messageRepository.findByFromAndToOrToAndFromOrFromAndTo(
                getMessagesParam.getAccountID(),
                getMessagesParam.getFriendID(),
                getMessagesParam.getAccountID(),
                getMessagesParam.getFriendID(),
                "sys" + getMessagesParam.getAccountID(),
                getMessagesParam.getAccountID(),
                pageable);


        Collections.reverse(messages);

        //拼装返回的messageHisVo
        List<MessageHisVo> messageHisVos = new ArrayList<>();

        messages.forEach(message -> {

            // 未读消息 修改为已读
            if (message.getStatus().equals(ConstUtil.MSG_UNREAD)) {
                message.setStatus(ConstUtil.MSG_READ);
                messageRepository.save(message);
            }

            if (user.getId().equals(message.getFrom())) {
                // from 是 user
                messageHisVos.add(
                        new MessageHisVo(
                                user.getId(),
                                user.getUserName(),
                                friend.getId(),
                                friend.getUserName(),
                                message.getStatus(),
                                message.getMsgType(),
                                message.getContent(),
                                message.getCreateTime()));
            } else {
                // from是 friend
                messageHisVos.add(
                        new MessageHisVo(
                                friend.getId(),
                                friend.getUserName(),
                                user.getId(),
                                user.getUserName(),
                                message.getStatus(),
                                message.getMsgType(),
                                message.getContent(),
                                message.getCreateTime()));
            }
        });

        return messageHisVos;
    }
}
