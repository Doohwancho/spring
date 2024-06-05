package org.example.com.security.config;


import org.example.com.security.config.jwt.JWTUtil;
import org.example.com.security.dto.UserLogin;
import org.example.com.security.util.Tokens;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

public class SpRefreshableIntegrationTest extends SpIntegrationTest {


    protected Tokens getToken(String username, String password) throws URISyntaxException {
        UserLogin login = UserLogin.builder().type(UserLogin.Type.login).username(username).password(password).build();
        HttpEntity<UserLogin> body = new HttpEntity<>(login);
        ResponseEntity<String> response = restTemplate.exchange(uri("/login"),
                HttpMethod.POST, body, String.class);
        return Tokens.builder()
                .accessToken(getAccessToken(response))
                .refreshToken(getRefreshToken(response))
                .build();
    }

    protected Tokens getRefreshToken(String refreshToken) throws URISyntaxException {
        UserLogin login = UserLogin.builder().type(UserLogin.Type.refresh)
                .refreshToken(refreshToken).build();
        HttpEntity<UserLogin> body = new HttpEntity<>(login);
        ResponseEntity<String> response = restTemplate.exchange(uri("/login"),
                HttpMethod.POST, body, String.class);
        return Tokens.builder()
                .accessToken(getAccessToken(response))
                .refreshToken(getRefreshToken(response))
                .build();
    }

    protected String getRefreshToken(ResponseEntity<String> response) {
        return response.getHeaders().get(JWTUtil.REFRESH_HEADER).get(0);
    }

}
