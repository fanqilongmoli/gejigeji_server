package com.gj.gejigeji.config;

import com.gj.gejigeji.model.Feed;
import com.gj.gejigeji.model.Henhouse;
import com.gj.gejigeji.model.Prop;
import com.gj.gejigeji.model.Theme;
import com.gj.gejigeji.repository.FeedRepository;
import com.gj.gejigeji.repository.HenhouseRepository;
import com.gj.gejigeji.repository.PropRepository;
import com.gj.gejigeji.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Lazy(false)
public class DbConfig implements CommandLineRunner {

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    PropRepository propRepository;

    @Autowired
    ThemeRepository themeRepository;

    @Autowired
    HenhouseRepository henhouseRepository;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("====初始化数据库====");

        //初始化饲料
        List<Feed> allFeed = feedRepository.findAll();
        if (allFeed.size()==0) {
            Feed feed1 = new Feed(null,"www.test.url","富硒饲料","富硒饲料吃了就是好",10f,1);
            Feed feed2 = new Feed(null,"www.test.url","有机饲料","有机饲料吃了就是好",10f,1);
            Feed feed3 = new Feed(null,"www.test.url","特质饲料","特质饲料吃了就是好",10f,1);
            feedRepository.save(feed1);
            feedRepository.save(feed2);
            feedRepository.save(feed3);
        }

        // 初始化主题
        if (themeRepository.findAll().size() == 0) {

            for (int i = 0; i < 3; i++) {
                Theme theme = new Theme(null,"www.zhuti.com","主题"+i,"描述",10f*i);
                Theme save = themeRepository.save(theme);

                for (int j = 0; j < 6; j++) {
                    Prop prop = new Prop(null,save.getId(),"www.test.url","道具1","道具1desc",10f,1);
                    propRepository.save(prop);
                }

            }

        }

        System.out.println("===================");
    }
}
