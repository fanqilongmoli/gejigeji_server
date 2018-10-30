package com.gj.gejigeji;

import com.gj.gejigeji.model.User;
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
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void delAll() {
        User ex = new User();
        ex.setPhone("18170036523");
        Optional<User> one = userRepository.findOne(Example.of(ex));
        final User user = one.get();
        String id = user.getId();

    }

}
