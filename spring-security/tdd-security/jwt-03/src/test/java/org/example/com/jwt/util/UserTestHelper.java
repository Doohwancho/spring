package org.example.com.jwt.util;

import lombok.AllArgsConstructor;
import org.example.com.jwt.domain.Authority;
import org.example.com.jwt.domain.User;
import org.example.com.jwt.service.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@AllArgsConstructor
public class UserTestHelper {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public User createUser(String name) throws DuplicateKeyException {
        User user = User.builder()
                .name(name)
                .email(name+"@test.com")
                .password(passwordEncoder.encode(name+"123"))
                .role("ROLE_USER")
                .authorities(Stream.of("ROLE_USER").map(Authority::new).collect(Collectors.toSet()))
                .enabled(true)
                .build();
        return userService.save(user);
    }

    public User createUser(String name, String... authorities) throws DuplicateKeyException {
        User user = createUser(name);
        Stream.of(authorities).forEach(auth->userService.addAuthority(user.getUserId(), auth));
        return user;
    }


    public User createAdmin(String name) {
        User user = User.builder()
                .name(name)
                .email(name+"@test.com")
                .password(passwordEncoder.encode(name+"123"))
                .role("ROLE_ADMIN")
                .authorities(Stream.of("ROLE_ADMIN").map(Authority::new).collect(Collectors.toSet()))
                .enabled(true)
                .build();
        return userService.save(user);
    }
    public User createAdmin(String name, String... authorities) throws DuplicateKeyException {
        User user = createAdmin(name);
        Stream.of(authorities).forEach(auth->userService.addAuthority(user.getUserId(), auth));
        return user;
    }
    public void assertUser(User user, String name){
        assertNotNull(user.getUserId());
        assertNotNull(user.getCreated());
        assertNotNull(user.getUpdated());
        assertTrue(user.isEnabled());
        assertEquals(name, user.getName());
        assertEquals(name+"@test.com", user.getEmail());
//        assertEquals(name+"123", user.getPassword());
    }

    public void assertUser(User user, String name, String... authorities){
        assertUser(user, name);
        assertTrue(user.getAuthorities().containsAll(
                Stream.of(authorities).map(auth->new Authority(auth)).collect(Collectors.toList())
        ));
    }


}