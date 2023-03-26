package org.example.com.security.config.filter;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.com.security.domain.User;
import org.example.com.security.dto.UserLogin;
import org.example.com.security.config.jwt.JWTUtil;
import org.example.com.security.dto.VerifyResult;
import org.example.com.security.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

public class RefreshableJWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private UserService userService;
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public RefreshableJWTLoginFilter(AuthenticationManager authenticationManager, UserService userService, JWTUtil jwtUtil, ObjectMapper objectMapper){
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
        setFilterProcessesUrl("/login");
    }

    //로그인 시도시, 아이디, 비밀번호로 authenticationManager를 통해 인증을 시도하고, 인증이 성공하면 successfulAuthentication 메소드가 실행됨
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserLogin userLogin = objectMapper.readValue(request.getInputStream(), UserLogin.class);

        //case1) id/pw 으로 authenticate하는 방식
        if(userLogin.getType().equals(UserLogin.Type.login)){
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userLogin.getUsername(), userLogin.getPassword(), null
            );
            return authenticationManager.authenticate(authToken);
        } else if(userLogin.getType().equals(UserLogin.Type.refresh)){ //case2) refresh token으로 authenticate 하는 방식
            if(StringUtils.isEmpty(userLogin.getRefreshToken()))
                throw new IllegalArgumentException("리프레쉬 토큰이 필요함. :" + userLogin.getRefreshToken());

            VerifyResult result = jwtUtil.verify(userLogin.getRefreshToken());
            if(result.isResult()){
                User user = userService.findUser(result.getUserId()).orElseThrow(() -> new UsernameNotFoundException("알 수 없는 유저입니다."));
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            } else {
                throw new TokenExpiredException("리프레쉬 토큰 만료", Instant.EPOCH);
            }
        } else {
            throw new IllegalArgumentException("알 수 없는 타입 : "+userLogin.getType());
        }
    }

    //인증이 성공하면, response header에 JWT를 추가해서 보내준다.
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException
    {
        User user = (User)authResult.getPrincipal();
        response.addHeader(JWTUtil.AUTH_HEADER, JWTUtil.BEARER+jwtUtil.generate(user.getUserId(), JWTUtil.TokenType.access));
        response.addHeader(JWTUtil.REFRESH_HEADER, jwtUtil.generate(user.getUserId(), JWTUtil.TokenType.refresh));
//        super.successfulAuthentication(request, response, chain, authResult);
    }

    //인증이 실패하면, 실패한 이유를 출력한다.
    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException
    {
        System.out.println(failed.getMessage());
        super.unsuccessfulAuthentication(request, response, failed);
    }
}