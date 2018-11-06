package com.gj.gejigeji;

import com.gj.gejigeji.model.Feed;
import com.gj.gejigeji.model.Theme;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.repository.FeedRepository;
import com.gj.gejigeji.repository.ThemeRepository;
import com.gj.gejigeji.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GejigejiApplicationTests {

    @Autowired
    ThemeRepository feedRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void delAll() {
        Theme entity = new Theme(null,"www.主题3.com","主题3","主题3" +
                "",10f);
        feedRepository.save(entity);

    }


}
