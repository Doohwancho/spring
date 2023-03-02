package com.practice.domain.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Id;

@Data
@Builder
@RedisHash("logoutAccessToken") //TODO - what does @RedisHash do?
@NoArgsConstructor
@AllArgsConstructor
public class LogoutAccessToken { //TODO - why just use refresh token? why need another access token for logout?
    //LogoutAccessToken이란것도, refresh token처럼 만들어서 관리하네?

    @Id
    private String id;

    private String username;

    @TimeToLive //TODO - what does @TimeToLive do?
    private Long expiration;

    public static LogoutAccessToken from(String username, String accessToken, Long expirationTime) {
        return LogoutAccessToken.builder()
                .id(accessToken)
                .username(username)
                .expiration(expirationTime / 1000)
                .build();
    }
}