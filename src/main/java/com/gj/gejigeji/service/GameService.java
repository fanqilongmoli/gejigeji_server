package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.NoCoinException;
import com.gj.gejigeji.exception.NoUserException;
import com.gj.gejigeji.model.Feed;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserChicken;
import com.gj.gejigeji.model.UserFeed;
import com.gj.gejigeji.repository.FeedRepository;
import com.gj.gejigeji.repository.UserChickenRepository;
import com.gj.gejigeji.repository.UserFeedRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.util.GejiProperties;
import com.gj.gejigeji.vo.*;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GameService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserChickenRepository UserChickenRepository;

    @Autowired
    UserFeedRepository userFeedRepository;

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    GejiProperties gejiProperties;

    /**
     * 获取用户的游戏次数
     *
     * @param actionParam
     * @return
     */
    public GameCountVo gameCount(ActionParam actionParam) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new NoUserException();
        }

        GameCountVo gameCountVo = new GameCountVo();

        gameCountVo.setMiniGameCount1(user.getMiniGameCount1());
        gameCountVo.setMiniGameCount2(user.getMiniGameCount2());
        gameCountVo.setMiniGameCount3(user.getMiniGameCount3());
        gameCountVo.setMiniGameCount4(user.getMiniGameCount4());

        return gameCountVo;
    }

//    /**
//     * 打地鼠开始
//     *
//     * @param actionParam
//     * @return
//     */
//    public GameResultVo ddsStart(ActionParam actionParam) {
//        User userEx = new User();
//        userEx.setId(actionParam.getAccountID());
//        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
//        if (user == null) {
//            throw new NoUserException();
//        }
//        GameResultVo gameResultVo = new GameResultVo();
//        Integer miniGameCount1 = user.getMiniGameCount1();
//        // 减少游戏次数
//        if (miniGameCount1 > 0) {
//            user.setMiniGameCount1(miniGameCount1 - 1);
//            //每天各有3次免费的机会，超过免费机会，每次10个金币
//            if (user.getMiniGameCount1() < 7) {
//                float newCoin = user.getCoin() - gejiProperties.getGameFree();
//                if (newCoin < 0) {
//                    throw new NoCoinException();
//                }
//                user.setCoin(newCoin);
//            }
//            userRepository.save(user);
//            gameResultVo.setAllow(true);
//            return gameResultVo;
//        }
//        gameResultVo.setAllow(false);
//        return gameResultVo;
//    }

    /**
     * 打地鼠结束
     *
     * @param ddsEndParam
     * @return
     */
    public GameResultVo ddsEnd(DDSEndParam ddsEndParam) {

//        //打中狼的个数
//        private Integer wolfCount;
//        //打中炸弹个数
//        private Integer bombCount;
//        //打中地鼠个数
//        private Integer diglettCount ;

        User userEx = new User();
        userEx.setId(ddsEndParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new NoUserException();
        }
        final Integer miniGameCount1 = user.getMiniGameCount1();

        if (miniGameCount1 > 0) {
            updateGameLikeValue(ddsEndParam.getAccountID());
        }


        GameResultVo gameResultVo = new GameResultVo();
        gameResultVo.setAllow(true);
        //计算奖励 生命数
        int sm = 3 - ddsEndParam.getBombCount();
        if (sm > 1) {
            int allFenshu = sm * 50 + ddsEndParam.getWolfCount() * 2 + ddsEndParam.getDiglettCount();
            if (allFenshu >= 200) {
                //有机饲料10包
                // 奖励ID
                gameResultVo.setAwardId(1);
                // 道具ID
                gameResultVo.setItemId(3);
                // 道具数量
                gameResultVo.setCount(10);
                Feed feedEx = new Feed();
                feedEx.setName("有机饲料");
                Feed feed = feedRepository.findOne(Example.of(feedEx)).orElse(null);
                if (feed != null) {
                    final UserFeed userFeed = userFeedRepository.findByUserIdAndFeedId(user.getId(), feed.getId()).orElse(null);
                    if (userFeed != null) {
                        userFeed.setAmount(userFeed.getAmount() + 10);
                        userFeedRepository.save(userFeed);
                    } else {
                        UserFeed userFeed1 = new UserFeed();
                        userFeed1.setAmount(10);
                        userFeed1.setPrice(0f);
                        userFeed1.setUserId(user.getId());
                        userFeed1.setAllPrice(0f);
                        userFeed1.setFeedId(feed.getId());
                        userFeedRepository.save(userFeed1);
                    }
                }
            } else if (allFenshu >= 100) {
                //免费游戏1次
                // 奖励ID
                gameResultVo.setAwardId(2);
                // 道具ID
                gameResultVo.setItemId(0);
                // 道具数量
                gameResultVo.setCount(1);
                user.setMiniGameCount3(user.getMiniGameCount1() + 1);
                userRepository.save(user);
            } else if (allFenshu >= 50) {
                //金币40
                updateUserCoin(user.getId(), 40f);
                // 奖励ID
                gameResultVo.setAwardId(3);
                // 道具ID
                gameResultVo.setItemId(1);
                // 道具数量
                gameResultVo.setCount(40);
            } else if (allFenshu >= 30) {
                //金币30
                updateUserCoin(user.getId(), 30f);
                // 奖励ID
                gameResultVo.setAwardId(4);
                // 道具ID
                gameResultVo.setItemId(1);
                // 道具数量
                gameResultVo.setCount(30);
            } else if (allFenshu >= 10) {
                //金币20
                updateUserCoin(user.getId(), 20f);
                // 奖励ID
                gameResultVo.setAwardId(5);
                // 道具ID
                gameResultVo.setItemId(1);
                // 道具数量
                gameResultVo.setCount(20);
            } else {
                //金币10
                updateUserCoin(user.getId(), 10f);
                // 奖励ID
                gameResultVo.setAwardId(6);
                // 道具ID
                gameResultVo.setItemId(1);
                // 道具数量
                gameResultVo.setCount(10);
            }

        } else {
            gameResultVo.setAllow(false);
        }
        return gameResultVo;
    }

    /**
     * 老虎机开始
     *
     * @param actionParam
     * @return
     */
    public GameResultVo lhjStart(ActionParam actionParam) {

        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new NoUserException();
        }
        // 减少游戏次数
        Integer miniGameCount3 = user.getMiniGameCount3();

        if (miniGameCount3 > 0) {
            user.setMiniGameCount3(miniGameCount3 - 1);
        }
        //每天各有3次免费的机会，超过免费机会，每次10个金币
        if (user.getMiniGameCount3() > 0 ) {
            float newCoin = user.getCoin() - gejiProperties.getGameFree();
            if (newCoin < 0) {
                throw new NoCoinException();
            }
            user.setCoin(newCoin);
        }

        userRepository.save(user);

        // 添加游戏的好感度
        if (miniGameCount3 > 0) {
            updateGameLikeValue(actionParam.getAccountID());
        }
        GameResultVo gameResultVo = new GameResultVo();

        // 随机生成奖励
        int i = RandomUtils.nextInt(1, 101);
        gameResultVo.setAllow(true);
        if (i <= 1) {
            //有机饲料10包
            // 奖励ID
            gameResultVo.setAwardId(1);
            // 道具ID
            gameResultVo.setItemId(3);
            // 道具数量
            gameResultVo.setCount(10);
            Feed feedEx = new Feed();
            feedEx.setName("有机饲料");
            Feed feed = feedRepository.findOne(Example.of(feedEx)).orElse(null);
            if (feed != null) {
                final UserFeed userFeed = userFeedRepository.findByUserIdAndFeedId(user.getId(), feed.getId()).orElse(null);
                if (userFeed != null) {
                    userFeed.setAmount(userFeed.getAmount() + 10);
                    userFeedRepository.save(userFeed);
                } else {
                    UserFeed userFeed1 = new UserFeed();
                    userFeed1.setAmount(10);
                    userFeed1.setPrice(0f);
                    userFeed1.setUserId(user.getId());
                    userFeed1.setAllPrice(0f);
                    userFeed1.setFeedId(feed.getId());
                    userFeedRepository.save(userFeed1);
                }
            }
        } else if (i <= 2) {
            // 免费游戏1次
            // 奖励ID
            gameResultVo.setAwardId(2);
            // 道具ID
            gameResultVo.setItemId(0);
            // 道具数量
            gameResultVo.setCount(1);
            user.setMiniGameCount3(user.getMiniGameCount3() + 1);
            userRepository.save(user);
        } else if (i <= 3) {
            //金币20
            updateUserCoin(user.getId(), 20f);
            // 奖励ID
            gameResultVo.setAwardId(3);
            // 道具ID
            gameResultVo.setItemId(1);
            // 道具数量
            gameResultVo.setCount(20);
        } else if (i <= 5) {
            //金币15
            updateUserCoin(user.getId(), 15f);
            // 奖励ID
            gameResultVo.setAwardId(4);
            // 道具ID
            gameResultVo.setItemId(1);
            // 道具数量
            gameResultVo.setCount(15);
        } else if (i <= 8) {
            //金币10
            updateUserCoin(user.getId(), 10f);
            // 奖励ID
            gameResultVo.setAwardId(5);
            // 道具ID
            gameResultVo.setItemId(1);
            // 道具数量
            gameResultVo.setCount(10);
        } else if (i <= 12) {
            //金币5
            updateUserCoin(user.getId(), 5f);
            // 奖励ID
            gameResultVo.setAwardId(6);
            // 道具ID
            gameResultVo.setItemId(1);
            // 道具数量
            gameResultVo.setCount(5);
        } else if (i <= 32) {
            //金币1
            updateUserCoin(user.getId(), 1f);
            // 奖励ID
            gameResultVo.setAwardId(7);
            // 道具ID
            gameResultVo.setItemId(1);
            // 道具数量
            gameResultVo.setCount(1);
        } else {

            gameResultVo.setAllow(false);
        }
        return gameResultVo;


    }

    /**
     * 大转盘开始
     *
     * @param actionParam
     * @return
     */
    public GameResultVo dzbStart(ActionParam actionParam) {

        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new NoUserException();
        }
        // 减少游戏次数
        Integer miniGameCount2 = user.getMiniGameCount2();
        if (miniGameCount2 > 0) {
            user.setMiniGameCount2(miniGameCount2 - 1);
            userRepository.save(user);
        }
        //每天各有3次免费的机会，超过免费机会，每次10个金币
        if (user.getMiniGameCount2() > 0) {
            float newCoin = user.getCoin() - gejiProperties.getGameFree();
            if (newCoin < 0) {
                throw new NoCoinException();
            }
            user.setCoin(newCoin);
        }
        userRepository.save(user);

        // 添加游戏的好感度
        if (user.getMiniGameCount2() > 0) {
            updateGameLikeValue(actionParam.getAccountID());
        }

        GameResultVo gameResultVo = new GameResultVo();
        // 随机生成奖励
        int i = RandomUtils.nextInt(1, 101);
        gameResultVo.setAllow(true);
        if (i <= 1) {
            //  有机饲料10包
            // 奖励ID
            gameResultVo.setAwardId(1);
            // 道具ID
            gameResultVo.setItemId(3);
            // 道具数量
            gameResultVo.setCount(10);
            Feed feedEx = new Feed();
            feedEx.setName("有机饲料");
            Feed feed = feedRepository.findOne(Example.of(feedEx)).orElse(null);
            if (feed != null) {
                final UserFeed userFeed = userFeedRepository.findByUserIdAndFeedId(user.getId(), feed.getId()).orElse(null);
                if (userFeed != null) {
                    userFeed.setAmount(userFeed.getAmount() + 10);
                    userFeedRepository.save(userFeed);
                } else {
                    UserFeed userFeed1 = new UserFeed();
                    userFeed1.setAmount(10);
                    userFeed1.setPrice(0f);
                    userFeed1.setUserId(user.getId());
                    userFeed1.setAllPrice(0f);
                    userFeed1.setFeedId(feed.getId());
                    userFeedRepository.save(userFeed1);
                }
            }
        } else if (i <= 2) {
            // 免费游戏1次
            // 奖励ID
            gameResultVo.setAwardId(2);
            // 道具ID
            gameResultVo.setItemId(0);
            // 道具数量
            gameResultVo.setCount(1);
            user.setMiniGameCount3(user.getMiniGameCount3() + 1);
            userRepository.save(user);
        } else if (i <= 22) {
            //金币5
            updateUserCoin(user.getId(), 5f);
            // 奖励ID
            gameResultVo.setAwardId(3);
            // 道具ID
            gameResultVo.setItemId(1);
            // 道具数量
            gameResultVo.setCount(5);
        } else if (i <= 32) {
            //金币20
            updateUserCoin(user.getId(), 20f);
            // 奖励ID
            gameResultVo.setAwardId(4);
            // 道具ID
            gameResultVo.setItemId(1);
            // 道具数量
            gameResultVo.setCount(20);
        } else if (i <= 33) {
            //金币50
            updateUserCoin(user.getId(), 50f);
            // 奖励ID
            gameResultVo.setAwardId(5);
            // 道具ID
            gameResultVo.setItemId(1);
            // 道具数量
            gameResultVo.setCount(50);
        } else {
            // 奖励ID
            gameResultVo.setAwardId(6);
            // 道具ID
            gameResultVo.setItemId(-1);
            // 道具数量
            gameResultVo.setCount(0);
        }
        return gameResultVo;

    }

//    /**
//     * 打怪兽开始
//     *
//     * @param actionParam
//     * @return
//     */
//    public GameResultVo dgsStart(ActionParam actionParam) {
//        User userEx = new User();
//        userEx.setId(actionParam.getAccountID());
//        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
//        if (user == null) {
//            throw new NoUserException();
//        }
//        GameResultVo gameResultVo = new GameResultVo();
//        Integer miniGameCount4 = user.getMiniGameCount4();
//
//        user.setMiniGameCount4(miniGameCount4 - 1);
//
//        //每天各有3次免费的机会，超过免费机会，每次10个金币
//        if (user.getMiniGameCount4() < 7) {
//            float newCoin = user.getCoin() - gejiProperties.getGameFree();
//            if (newCoin < 0) {
//                throw new NoCoinException();
//            }
//            user.setCoin(newCoin);
//        }
//
//        userRepository.save(user);
//        gameResultVo.setAllow(true);
//        return gameResultVo;
//
//
//    }

    /**
     * 打怪兽结束
     *
     * @param dgsEndParam
     * @return
     */
    public GameResultVo dgsEnd(DGSEndParam dgsEndParam) {

        User userEx = new User();
        userEx.setId(dgsEndParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new NoUserException();
        }

        // 添加游戏的好感度
        Integer miniGameCount4 = user.getMiniGameCount4();

        if (miniGameCount4 > 0) {
            updateGameLikeValue(dgsEndParam.getAccountID());
        }

//        @ApiModelProperty("最后剩余生命")
//        private Integer lifeCount;
//        @ApiModelProperty("是否胜利")
//        private Boolean isWin;
        GameResultVo gameResultVo = new GameResultVo();
        Boolean win = dgsEndParam.getWin();
        if (win) {
            gameResultVo.setAllow(true);
            int lifeCount = dgsEndParam.getLifeCount();
            if (lifeCount >= 5) {

                //  有机饲料10包

                Feed feedEx = new Feed();
                feedEx.setName("有机饲料");
                Feed feed = feedRepository.findOne(Example.of(feedEx)).orElse(null);
                if (feed != null) {
                    final UserFeed userFeed = userFeedRepository.findByUserIdAndFeedId(user.getId(), feed.getId()).orElse(null);
                    if (userFeed != null) {
                        userFeed.setAmount(userFeed.getAmount() + 10);
                        userFeedRepository.save(userFeed);
                    } else {
                        UserFeed userFeed1 = new UserFeed();
                        userFeed1.setAmount(10);
                        userFeed1.setPrice(0f);
                        userFeed1.setUserId(user.getId());
                        userFeed1.setAllPrice(0f);
                        userFeed1.setFeedId(feed.getId());
                        userFeedRepository.save(userFeed1);
                    }
                }

                // 奖励ID
                gameResultVo.setAwardId(1);
                // 道具ID
                gameResultVo.setItemId(3);
                // 道具数量
                gameResultVo.setCount(10);

            } else if (lifeCount >= 4) {

                // 免费游戏1次
                // 奖励ID
                gameResultVo.setAwardId(2);
                // 道具ID
                gameResultVo.setItemId(0);
                // 道具数量
                gameResultVo.setCount(1);

                user.setMiniGameCount3(user.getMiniGameCount4() + 1);
                userRepository.save(user);

            } else if (lifeCount >= 3) {
                updateUserCoin(user.getId(), 30f);
                // 奖励ID
                gameResultVo.setAwardId(3);
                // 道具ID
                gameResultVo.setItemId(1);
                // 道具数量
                gameResultVo.setCount(30);
            } else if (lifeCount >= 2) {
                updateUserCoin(user.getId(), 20f);
                // 奖励ID
                gameResultVo.setAwardId(4);
                // 道具ID
                gameResultVo.setItemId(1);
                // 道具数量
                gameResultVo.setCount(20);
            } else {
                updateUserCoin(user.getId(), 10f);
                // 奖励ID
                gameResultVo.setAwardId(5);
                // 道具ID
                gameResultVo.setItemId(1);
                // 道具数量
                gameResultVo.setCount(10);
            }
        } else {
            // 奖励ID
            gameResultVo.setAwardId(6);
            // 道具ID
            gameResultVo.setItemId(-1);
            // 道具数量
            gameResultVo.setCount(0);
        }
        return gameResultVo;
    }


    /**
     * 跟新好感度
     *
     * @param accountID
     */
    private void updateGameLikeValue(String accountID) {
        UserChicken UserChickenEx = new UserChicken();
        UserChickenEx.setUserId(accountID);
        UserChicken UserChicken = UserChickenRepository.findOne(Example.of(UserChickenEx)).orElse(null);
        if (UserChicken != null) {
            Date lastTime = new Date();
            UserChicken.setGame(UserChicken.getGame() + 3);
            UserChicken.setGameLastTime(lastTime);
            UserChickenRepository.save(UserChicken);


        }
    }

    /**
     * 每个游戏每天各有3次免费的机会，超过免费机会，每次10个金币
     *
     * @param actionParam
     * @param game        游戏类型 1打地鼠 2大转盘 3老虎机 4打怪兽
     * @return
     */
    public GameFreeResult checkGame4Free(ActionParam actionParam, String game) {
        User userEx = new User();
        userEx.setId(actionParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new NoUserException();
        }
        GameFreeResult gameFreeResult = new GameFreeResult();
        switch (game) {
            case "1":
                // 打地鼠

                Integer miniGameCount1 = user.getMiniGameCount1();
                // 减少游戏次数
                if (miniGameCount1 > 0) {
                    user.setMiniGameCount1(miniGameCount1 - 1);
                }

                userRepository.save(user);
                if (user.getMiniGameCount1() <= 0) {

                    gameFreeResult.setOk(false);
                    gameFreeResult.setEnough(user.getCoin() > gejiProperties.getGameFree());
                    gameFreeResult.setFee(gejiProperties.getGameFree());

                    //每天各有3次免费的机会，超过免费机会，每次10个金币
                    float newCoin = user.getCoin() - gejiProperties.getGameFree();
                    if (newCoin > 0) {
                        user.setCoin(newCoin);
                    }
                } else {
                    gameFreeResult.setOk(true);
                    gameFreeResult.setEnough(true);
                    gameFreeResult.setFee(0);
                }
                return gameFreeResult;

            case "2":
                // 大转盘
                if (user.getMiniGameCount2() <= 0) {
                    gameFreeResult.setOk(false);
                    gameFreeResult.setEnough(user.getCoin() > gejiProperties.getGameFree());
                    gameFreeResult.setFee(gejiProperties.getGameFree());
                } else {

                    gameFreeResult.setOk(true);
                    gameFreeResult.setEnough(true);
                    gameFreeResult.setFee(0);
                }
                return gameFreeResult;

            case "3":
                // 老虎机
                if (user.getMiniGameCount3() <= 0) {
                    gameFreeResult.setOk(false);
                    gameFreeResult.setEnough(user.getCoin() > gejiProperties.getGameFree());
                    gameFreeResult.setFee(gejiProperties.getGameFree());
                } else {
                    gameFreeResult.setOk(true);
                    gameFreeResult.setEnough(true);
                    gameFreeResult.setFee(0);
                }
                return gameFreeResult;

            case "4":
                // 打怪兽
                Integer miniGameCount4 = user.getMiniGameCount4();
                // 减少游戏次数
                if (miniGameCount4 > 0) {
                    user.setMiniGameCount4(miniGameCount4 - 1);
                }

                userRepository.save(user);

                if (user.getMiniGameCount4() <= 0) {
                    gameFreeResult.setOk(false);
                    gameFreeResult.setEnough(user.getCoin() > gejiProperties.getGameFree());
                    gameFreeResult.setFee(gejiProperties.getGameFree());

                    //每天各有3次免费的机会，超过免费机会，每次50个金币
                    float newCoin = user.getCoin() - gejiProperties.getGameFree();
                    if (newCoin > 0) {
                        user.setCoin(newCoin);
                    }

                } else {
                    gameFreeResult.setOk(true);
                    gameFreeResult.setEnough(true);
                    gameFreeResult.setFee(0);
                }
                return gameFreeResult;

        }
        gameFreeResult.setOk(false);
        return gameFreeResult;
    }


    private void updateUserCoin(String userId, float coin) {
        final User user = userRepository.findById(userId).get();
        final float v = user.getCoin() + coin;
        user.setCoin(v);
        userRepository.save(user);
    }
}
