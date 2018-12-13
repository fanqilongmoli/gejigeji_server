package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Friends;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FriendsRepository extends MongoRepository<Friends,String> {
    /**
     * 查询好友列表中 我为userID 对方为 friendID  或者 我为friendID 对方为userID
     * @param userId
     * @param friendId
     * @param friendId1
     * @param userId1
     * @return
     */
    Optional<Friends> findByUserIdAndFriendIdOrFriendIdAndUserId(String userId,String friendId,String friendId1,String userId1);



    List<Friends> findByUserIdAndAndStatus(String userId,int status);

    List<Friends> findByFriendIdAndAndStatus(String friend,int status);

    List<Friends> findByFriendIdAndStatusOrUserIdAndStatusOrderByLastMsgTime(String friend,int status1,String userId,int status2);

    Optional<Friends> findByUserIdAndFriendId(String userId,String friendId);



}
