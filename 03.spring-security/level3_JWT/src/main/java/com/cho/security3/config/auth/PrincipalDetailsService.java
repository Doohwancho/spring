package com.cho.security3.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cho.security3.model.User;
import com.cho.security3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/* 
 * 
 * 원래 localhost:8080/login 하면, 
 * SecurityConfig에서 .formLogin().loginProcessingUrl("/loginProc");
 * 하면 일로 와서 처리됬었는데, 
 * 
 * jwt쓰기로 했으니까, .formLogin().disable() 해버려서 localhost:8080/login로 요청이 안옴.
 * 그래서 수동으로 얘를 떄려주는 필터를 만들어야 함.
 * 그게 JwtAuthenticationFilter.java  
 * 
 * */
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("PrincipalDetailsService : 진입");
		User user = userRepository.findByUsername(username);

		// session.setAttribute("loginUser", user);
		return new PrincipalDetails(user);
	}
}

