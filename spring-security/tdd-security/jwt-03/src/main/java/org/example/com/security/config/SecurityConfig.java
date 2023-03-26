package org.example.com.security.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.com.security.config.filter.JWTCheckFilter;
import org.example.com.security.config.filter.RefreshableJWTLoginFilter;
import org.example.com.security.config.jwt.JWTUtil;
import org.example.com.security.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
        final RefreshableJWTLoginFilter jwtLoginFilter = new RefreshableJWTLoginFilter(authenticationManager(), userService, jwtUtil, objectMapper);
        final JWTCheckFilter checkFilter = new JWTCheckFilter(authenticationManager(), userService, jwtUtil);
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtLoginFilter) //로그인 authentication 통과하면,
                .addFilter(checkFilter) //jwt token 검증함.
        ;
    }
}