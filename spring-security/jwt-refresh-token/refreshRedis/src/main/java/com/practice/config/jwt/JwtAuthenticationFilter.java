package com.practice.config.jwt;


import com.practice.util.JwtTokenUtil;
import com.practice.util.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 필터로 넘어온 토큰을 검증하는 코드입니다.
 *
 * 토큰 검증은 개발자가 추가하기 나름이지만  저의 프로젝트의 경우, 기본적으로 아래와 같이 검증합니다.

 * 1. 헤더에서 "Authorization" 에 해당하는 value를 뽑는다.
 *     "Authorization" :  "Bearer-{토큰 값}"
 *
 * 2. "Bearer-" 를 제외한 문자열(토큰 값)을 뽑아낸다.
 *
 * 3. 뽑아낸 토큰 값을 토대로 아래의 여러 검증 과정을 수행합니다.
 *      3-1.유효기간 검증
 *      3-2. 사용자명(id) 추출
 *      3-3. 추출 된 사용자명(id) 이 DB에 저장되어 있는지
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { //TODO - why extends OncePerRequestFilter?

    //TODO - 이 두 필드 모두 user defined custom class인데?
    private final TokenManager tokenManager;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.resolveTokenFromRequest(request);

        if (token != null && this.tokenManager.validateToken(token)) {
            Authentication auth = this.tokenManager.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            log.debug(String.format("[%s] -> %s", jwtTokenUtil.parseToken(token), request.getRequestURI()));
        }
        filterChain.doFilter(request, response);
    }

    private String resolveTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        return jwtTokenUtil.resolveToken(token);
    }
}