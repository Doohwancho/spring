package com.cho.bbs.config;

import com.cho.bbs.config.auth.CustomOAuth2UserService;
import com.cho.bbs.domain.user.Role;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;


@EnableGlobalMethodSecurity(prePostEnabled = true) //prePostEnabled(@PreAuthorize, @PostAuthorize 애노테이션을 사용하여 인가 처리를 하고 싶을때 사용하는 옵션)
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig { //extends WebSecurityConfigurerAdapter

    private final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
            .csrf().disable()
            .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션을 disable.
                .and()
            .authorizeRequests() //URL 별 권한 관리를 설정하는 옵션의 시작점. 이 부분이 선언되어야 antMatchers 옵션을 사용할 수 있다.
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**").permitAll()
//                .antMatchers("/api/v1/posts/**").hasRole(Role.USER.name())
                .anyRequest().authenticated() //설정된 값들 이외 나머지 URL을 로그인 한 인증도니 사용자에게 만 허용
                .and()
            .logout()
                .logoutSuccessUrl("/") //로그아웃 성공 시, / 주소로 리다이렉트
                .and()
            .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        return http.build();
    }
}
