package com.cho.security1.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cho.security1.model.User;
import com.cho.security1.repository.UserRepository;


//SecurityConfig.java에서 .loginProcessingUrl("/loginProc") 에 내부적으로 관여하는 코드.
//"/login" 요청 오면, 자동으로 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername() 실행. 
@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	//시큐리티 session은 Authentication 타입만 관리하는데, Authentication 타입은 UserDetails 타입을 받음.
	//security session(Authentication(UserDetails(PrincipalDetails(User)))
	//그걸 얘가 반환해주는 것. 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //이 username은 login.html에 <input type="text" name="username" /> 에 username과 같다. 
		User user = userRepository.findByUsername(username);
		if(user == null) {
			return null;
		}
		return new PrincipalDetails(user); //UserDetails 타입 반환 
	}

}