package me.hajoo.dynamic_insert_update.domain;

import me.hajoo.dynamic_insert_update.repository.UserRepository;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

//    @RepeatedTest(5)
    @Test
    public void 유저생성() throws Exception { //takes 7 seconds
        //given
        long start = System.currentTimeMillis();
        //when
        for (int i = 0; i < 50000; i++) {
            userRepository.save(new User("columnA"));
        }
        //then
        System.out.println((System.currentTimeMillis() - start) / 1000 + "s");
    }

//    @RepeatedTest(5)
    @Test
    public void 유저수정() throws Exception { //takes 7 seconds
        //given
        User user = userRepository.save(new User("columnA"));
        long start = System.currentTimeMillis();
        //when
        for (int i = 0; i < 50000; i++) {
            user.changeColumnA("columnA" + i);
            entityManager.flush();
        }
        //then
        System.out.println((System.currentTimeMillis() - start) / 1000 + "s");
    }

}
