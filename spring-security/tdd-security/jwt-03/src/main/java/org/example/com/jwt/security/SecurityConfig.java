package org.example.com.jwt.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.com.jwt.security.filter.JWTCheckFilter;
import org.example.com.jwt.security.filter.JWTLoginFilter;
import org.example.com.jwt.security.jwt.JWTUtil;
import org.example.com.jwt.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final JWTUtil jwtUtil ;

    public SecurityConfig(UserService userService, ObjectMapper objectMapper, JWTUtil jwtUtil) {
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JWTLoginFilter jwtLoginFilter = new JWTLoginFilter(authenticationManager(), jwtUtil, objectMapper);
        JWTCheckFilter checkFilter = new JWTCheckFilter(authenticationManager(), userService, jwtUtil);
        http
                .csrf().disable()
                .addFilter(jwtLoginFilter) //로그인 authentication 통과하면,
                .addFilter(checkFilter) //jwt token 검증함.
        ;
    }
}