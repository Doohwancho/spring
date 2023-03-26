package org.example.jwt.security.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.jwt.security.jwt.JWTUtil;
import org.example.jwt.domain.User;
import org.example.jwt.dto.UserLogin;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public JWTLoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, ObjectMapper objectMapper){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
        setFilterProcessesUrl("/login");
    }

    //로그인 시도시, 아이디, 비밀번호로 authenticationManager를 통해 인증을 시도하고, 인증이 성공하면 successfulAuthentication 메소드가 실행됨
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserLogin userLogin = objectMapper.readValue(request.getInputStream(), UserLogin.class);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userLogin.getUsername(), userLogin.getPassword(), null
        );
        return authenticationManager.authenticate(authToken);
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
        response.addHeader(JWTUtil.AUTH_HEADER, JWTUtil.BEARER+jwtUtil.generate(user.getUserId()));
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