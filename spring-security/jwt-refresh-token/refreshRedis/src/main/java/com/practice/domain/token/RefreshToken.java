package com.practice.domain.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Id;

//TODO - Q. jwt는 stateless해서 쓰는 목적인데, refresh token은 redis던, mysql던 저장하잖아? 그럼 stateful 이잖아?
//A. stateless인 jwt를 쓰면, stateless함에서 오는 장점(ex. scale out)이 있지만,
//   서버가 상태를 보관하고 있지 않기 때문에, jwt token 얘 하나 털리면 끝.
//  근데 refresh token을 쓰면, stateful하긴 해도, 서버는 발급한 토큰에 대한 제어권을 가지고 있다는 장점이 있다. +

// Refresh Token의 목적은 Access Token의 유효 기간을 짧고, 자주 재발급 하도록 만들어
// 보안을 강화하면서도 사용자에게 잦은 로그아웃 경험을 주지 않도록 하는 것이다.

//TODO - db에 저장되는데 @Entity가 없네?
//A. redis에 넣는 애거든.
//Q. 왜?
//A. reissue()할 떄, MemberServiceImpl.createRefreshToken()할 떄, db에서 검증하려고 꺼낸 refresh token을
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("refreshToken") //TODO - why use @RedisHash()?
//A. redis는 in-memory data structure for caching, managing session state.
//1. redis is fast and reliable way to store and retrieve refresh token
//2. secure since data is stored in-memory, not persist on disk. -> 어짜피 데이터가 영속적이지 않은게, refresh-token과 궁합이 잘 맞음.
//3. db에 refresh token을 저장하면, db io 때 마다 병목 일어나잖아? + 일정 시간 이후 만료 시키는 프로시저 돌리는게 좀 별로다. 반면 redis는 자체 메서드에 데이터 유효기간 정할 수 있는게 있어서 개꿀
@Builder
public class RefreshToken {
    @Id
    private String id;
    private String refreshToken;

    @TimeToLive //TODO - redis의 TimeToLive로, expiration 시간을 제한하면, db io 없이, redis가 자체적으로 시간 떙 되면 지워버림
    // mysql로 db에 저장된거 지우려면, 프로시저 써서 지워야 했던걸, redis는 간단히 해결 가능. 개꿀.
    private Long expiration;

    public static RefreshToken from(String username, String refreshToken, Long expirationTime) {
        return RefreshToken.builder()
                .id(username)
                .refreshToken(refreshToken)
                .expiration(expirationTime / 1000)
                .build();
    }
}