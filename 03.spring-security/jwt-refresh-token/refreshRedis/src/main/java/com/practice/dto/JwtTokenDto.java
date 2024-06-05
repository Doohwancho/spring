package com.practice.dto;

import com.practice.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtTokenDto { //TODO - 얘가 실질적으로 jwt-access-token 역할을 하네. domain에는 없지만.
    private String grantType; //"Bearer-";
    private String accessToken;

    public static JwtTokenDto of(String accessToken) {
        return JwtTokenDto.builder()
                .grantType(JwtTokenUtil.TOKEN_PREFIX)
                .accessToken(accessToken)
                .build();
    }
}