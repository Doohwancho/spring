package com.cho.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // @Secured("ROLE_MANAGER")나 @PreAuthorize("hasRole('ROLE_MANAGER')") 쓰기 위해 필요. //prePostEnabled는 @PostAuthorize("hasRole('ROLE_MANAGER')")  쓰기 위해 사용 
public class SecurityConfig extends WebSecurityConfigurerAdapter{ //Deprecated - WebSecurityConfigurerAdapter
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable(); //서버가 응답한 html 페이지로 요청했는지, 아니면 강제로 포스트맨.같은걸로 요청했는지를 검증하는데 사용됨. 그거 해제안하면 포스트맨 테스트가 안됨. 
		//요즘도 disable()함. 원래는 프론트를 .jsp, .mustache로 만들고 form태그 썼는데, 지금은 페이지 자체를 리엑트에서 만들고, 자바스크립트 요청만 해서 csrf를 안쓴다.  
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated() //인증만 되면 들어갈 수 있는 주소. 
			//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
			//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
		.and()
			.formLogin()
			.loginPage("/login") //이 주소가 호출되면, 시큐리티가 낚아채서 로그인 처리해줌. 
			.loginProcessingUrl("/loginProc")
			.defaultSuccessUrl("/"); //기본은 로그인하면 /로 이동시켜주는데, /user가려다 로그인창 떠서 로그인하면, /user로 보내줌. 개꿀! 
	}
}

