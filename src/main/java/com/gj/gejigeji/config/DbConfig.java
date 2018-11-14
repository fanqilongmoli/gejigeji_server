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

        // 初始化鸡舍
        if (henhouseRepository.findAll().size() == 0) {
            Henhouse henhouse = new Henhouse();
            henhouse.setPrice(1000f);
            henhouse.setUrl("www.jishe1.com");
            henhouse.setDesc("鸡舍1desc");
            henhouse.setName("鸡舍1");
            henhouseRepository.save(henhouse);
        }

        //初始化道具
        List<Prop> allProp = propRepository.findAll();
        if (allProp.size()==0){
            Prop prop1 = new Prop(null,"www.test.url","道具1","道具1desc",10f,1);
            Prop prop2 = new Prop(null,"www.test.url","道具2","道具2desc",10f,1);
            Prop prop3 = new Prop(null,"www.test.url","道具3","道具3desc",10f,1);
            Prop prop4 = new Prop(null,"www.test.url","道具4","道具4desc",10f,1);
            Prop prop5 = new Prop(null,"www.test.url","道具5","道具5desc",10f,1);
            propRepository.save(prop1);
            propRepository.save(prop2);
            propRepository.save(prop3);
            propRepository.save(prop4);
            propRepository.save(prop5);
        }
        // 初始化主题
        if (themeRepository.findAll().size() == 0) {
            Theme theme1 = new Theme(null,"www.zhuti.com","主题1","主题1desc",100f);
            Theme theme2 = new Theme(null,"www.zhuti.com","主题2","主题2desc",100f);
            Theme theme3 = new Theme(null,"www.zhuti.com","主题3","主题3desc",100f);
            Theme theme4 = new Theme(null,"www.zhuti.com","主题4","主题4desc",100f);
            Theme theme5 = new Theme(null,"www.zhuti.com","主题5","主题5desc",100f);

            themeRepository.save(theme1);
            themeRepository.save(theme2);
            themeRepository.save(theme3);
            themeRepository.save(theme4);
            themeRepository.save(theme5);

        }

        System.out.println("===================");
    }
}
