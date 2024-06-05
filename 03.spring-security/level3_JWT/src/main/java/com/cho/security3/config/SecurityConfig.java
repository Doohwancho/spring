package com.cho.security3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cho.security3.config.jwt.filter.JwtAuthenticationFilter;
import com.cho.security3.config.jwt.filter.JwtAuthorizationFilter;
import com.cho.security3.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CorsConfig corsConfig;
	
	/* jwt의 아주 기초적인 포멧 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.addFilter(corsConfig.corsFilter()) // CORS정책에서 벗어날 수 있다. @CrossOrigin도 가능한데, 인증 없을 때 씀. 인증 필요할 땐 시큐리티 필터에 등록해 주어야 함. 
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //session을 사용하지 않겠다라는 뜻. stateless server로 만들겠다는 말. 	
			.and()
				.formLogin().disable() //spring security가 제공하는 내부 login 처리 안쓸꺼야! jwt 쓸꺼야! 
				.httpBasic().disable() //http basic 방식 안쓰고 bearer 방식 사용. 
				
				.addFilter(new JwtAuthenticationFilter(authenticationManager())) 
				.addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
				
				.authorizeRequests()
				.antMatchers("/api/v1/user/**")
				.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/api/v1/manager/**")
					.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/api/v1/admin/**")
					.access("hasRole('ROLE_ADMIN')")
				.anyRequest().permitAll();
	}
}

