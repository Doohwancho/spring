package com.practice.util;

import com.practice.config.security.CustomUserDetailService;
import com.practice.exception.message.ExceptionMessage;
import com.practice.exception.model.TokenCheckFailException;
import com.practice.service.token.LogoutAccessTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenManager {

    private final CustomUserDetailService customUserDetailService;
    private final LogoutAccessTokenService logoutAccessTokenService;
    private final JwtTokenUtil jwtTokenUtil;

    //TODO - JwtAuthenticationFilter.doFilterInternal 에서 옴.
    public Authentication getAuthentication(String jwt) {
        String tokenUserName = this.jwtTokenUtil.parseToken(jwt);

        if (tokenUserName == null) {
            throw new TokenCheckFailException(ExceptionMessage.MISMATCH_USERNAME_TOKEN);
        }

        UserDetails userDetails = customUserDetailService.loadUserByUsername(tokenUserName);

        if (!userDetails.getUsername().equals(tokenUserName)) {
            throw new TokenCheckFailException(ExceptionMessage.MISMATCH_USERNAME_TOKEN);
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) {
            throw new TokenCheckFailException(ExceptionMessage.FAIL_TOKEN_CHECK);
        } else if (this.jwtTokenUtil.parseClaims(token).getExpiration().before(new Date())) {
            throw new TokenCheckFailException(ExceptionMessage.TOKEN_VALID_TIME_EXPIRED);
        } else if (checkLogout(token)) { //TODO - logout 되었을 때, 해커가 jwt token들고 authorize()나 reissue() 하려고 하면, LogoutAccessTokenService.existsLogoutAccessTokenById()를 통해서 방어하는 코드
            throw new TokenCheckFailException(ExceptionMessage.ALREADY_LOGOUT_USER);
        }
        return true;
    }

    private boolean checkLogout(String token) {
        return logoutAccessTokenService.existsLogoutAccessTokenById(token);
    }
}
