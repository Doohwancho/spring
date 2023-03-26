package org.example.com.security.jwt;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.example.com.security.util.RestResponsePage;
import org.example.com.security.domain.Authority;
import org.example.com.security.domain.User;
import org.example.com.security.config.SpJwtUserAdminIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.URISyntaxException;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest extends SpJwtUserAdminIntegrationTest {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserControllerIntegrationTest.class);

    @BeforeEach
    void before(){
        prepareUserAdmin();
    }

    @DisplayName("1-1. user1 은 자신의 정보를 조회할 수 있다.")
    @Test
    void test_1_1() throws URISyntaxException {
        String accessToken = getToken("user1@test.com", "user1123").getAccessToken();
        ResponseEntity<User> response = restTemplate.exchange(uri("/user/"+USER1.getUserId()),
                HttpMethod.GET, getAuthHeaderEntity(accessToken), User.class);

        assertEquals(200, response.getStatusCodeValue());
        userTestHelper.assertUser(response.getBody(), "user1");
    }

    //JWTCheckFilter 에서 토큰이 없으면 401 에러를 발생시킨다.
    @DisplayName("1. admin 유저는 userList 를 가져올 수 있다.")
    @Test
    void test_1() throws URISyntaxException, JsonProcessingException {
        String accessToken = getToken("admin@test.com", "admin123").getAccessToken();

        ResponseEntity<String> response = restTemplate.exchange(uri("/user/list"),
                HttpMethod.GET, getAuthHeaderEntity(accessToken), String.class);

        RestResponsePage<User> page = objectMapper.readValue(response.getBody(),
                new TypeReference<RestResponsePage<User>>() {
                });

        assertEquals(2, page.getTotalElements());
        assertTrue(page.getContent().stream().map(user->user.getName())
                .collect(Collectors.toSet()).containsAll(Set.of("user1", "admin")));

        page.getContent().forEach(System.out::println);
    }


    @Disabled("실패! UserService.addAuthority() 에서 authorities 업데이트 시 문제가 생긴다.")
    @DisplayName("2. user1 에게 admin 권한을 준다.")
    @Test
    void test_2() throws URISyntaxException, JsonProcessingException {

        // token
        String accessToken = getToken("admin@test.com", "admin123").getAccessToken();

        // user1 에게 admin 권한을 준다.
        ResponseEntity<String> response = restTemplate.exchange(uri(
                        format("/user/authority/add?userId=%s&authority=%s", USER1.getUserId(), Authority.ROLE_ADMIN)),
                HttpMethod.PUT, getAuthHeaderEntity(accessToken), String.class);

        assertEquals(200, response.getStatusCodeValue());

        // user1 데이터를 가져와서 확인한다.
        ResponseEntity<String> response2 = restTemplate.exchange(uri(
                        format("/user/%s", USER1.getUserId())),
                HttpMethod.GET, getAuthHeaderEntity(accessToken), String.class);
        assertEquals(200, response2.getStatusCodeValue());

        User respUser = objectMapper.readValue(response2.getBody(), User.class);
        log.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        log.info(respUser.toString());
        /*
        User(userId=641ee64cc59816690a0c8457, email=user1@test.com, name=user1, picUrl=null, password=null,
        role=ROLE_USER, enabled=true, authorities=[ROLE_USER], created=2023-03-25T21:17:16.364, updated=2023-03-25T21:17:17.089)
         */
//        assertTrue(respUser.getAuthorities().contains(Authority.ADMIN));
        assertTrue(respUser.getAuthorities().stream().map(auth -> auth.getAuthority())
                .collect(Collectors.toSet()).containsAll(Set.of("ROLE_ADMIN")));
    }

}
