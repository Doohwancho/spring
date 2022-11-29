package me.hajoo.bulk_insert;

import me.hajoo.bulk_insert.user.User;
import me.hajoo.bulk_insert.user.UserJdbcRepository;
import me.hajoo.bulk_insert.user.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserJdbcRepository userJdbcRepository;

    static List<User> users;

    @BeforeAll
    static void setup() {
        users = new ArrayList<>();
        for (int i = 0; i < 60000; i++) {
            users.add(new User("email", "password", "name"));
        }
    }

    @Test
    @DisplayName("6만 건 단일 save")
    public void testSave() throws Exception {
        long start = System.currentTimeMillis();
        users.forEach(user -> userRepository.save(user));
        System.out.println((System.currentTimeMillis() - start) / 1000 + "ms");
    }

    @Test
    @DisplayName("6만 건 saveAll")
    public void testSaveAll() throws Exception {
        long start = System.currentTimeMillis();
        userRepository.saveAll(users);
        System.out.println((System.currentTimeMillis() - start) / 1000 + "ms");
    }

    @Test
    @DisplayName("6만 건 bulk insert")
    public void testBulkInsert() throws Exception {
        long start = System.currentTimeMillis();
        userJdbcRepository.saveAll(users);
        System.out.println((System.currentTimeMillis() - start) / 1000 + "ms");
    }
}