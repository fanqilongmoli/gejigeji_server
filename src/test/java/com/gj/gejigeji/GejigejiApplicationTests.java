package com.gj.gejigeji;

import com.gj.gejigeji.model.*;
import com.gj.gejigeji.repository.*;
import com.gj.gejigeji.util.ConstUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GejigejiApplicationTests {

    @Autowired
    ThemeRepository feedRepository;

    @Autowired
    MailContentRepository mailContentRepository;

    @Autowired
    MailRepository mailRepository;


    @Test
    public void contextLoads() {
    }

    @Test
    public void delAll() {
        Theme entity = new Theme(null,"www.主题3.com","主题3","主题3" ,10f);
        feedRepository.save(entity);

    }
    @Test
    public void  testMail(){

        Mail entity = new Mail();
        entity.setUserId("5bd875d6d270a94ca1cadbee");
        entity.setTitle("测试邮件包含奖励");
        entity.setCreateTime(new Date());
        entity.setLogo("testlogo");
        entity.setType(ConstUtil.MAIL_TYPE_JL);
        entity.setRead(false);
        entity.setMessage("message消息");
        Mail save = mailRepository.save(entity);

        final MailContent entity1 = new MailContent();
        entity1.setItemCount(10);
        entity1.setMailId(save.getId());
        entity1.setItemId(1);
        mailContentRepository.save(entity1);


    }
}
