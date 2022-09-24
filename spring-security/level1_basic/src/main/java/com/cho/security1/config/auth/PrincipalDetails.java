package com.cho.security1.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cho.security1.model.User;

import lombok.Data;

//1. SecurityConfig에서 .loginPage("/login") 하면, 시큐리티가 낚아채서 로그인 진행.
//2. 근데 로그인 진행 만료되면, 유저 정보를 시큐리티가 관리하는 session에 넣어서 관리함. (Security ContextHolder) 
//3. 근데 요 시큐리티가 관리하는 session에 들어갈 수 있는 객체는 정해져있음. Authentication 타입 객체여야만 함. 
//4. Authentication 객체 안에 User정보가 있어야 함.
//5. 근데 Authentication 객체 안에 유저 정보 저장할 때 타입이 UserDetails 타입 이어야한 함.
//6. 따라서, User 오브젝트 타입 -> UserDetails 타입 객체로 감싸주면, User -> UserDetails -> Authentication -> Security Session managed by Security ContextHolder  


//나중에 Security Session 들어가서 Authentication객체 꺼내고, 그 안에 UserDetails객체 꺼내면, 그제서야 비로소 User 정보 얻을 수 있다. 

// Authentication 객체에 저장할 수 있는 유일한 타입
@Data
public class PrincipalDetails implements UserDetails{

	private User user;

	public PrincipalDetails(User user) {
		super();
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { //니 계정 만료됬니?
		return true; //아뇨?
	}

	@Override
	public boolean isAccountNonLocked() { //니 계정 잠겼니? 
		return true; //아뇨?
	}

	@Override
	public boolean isCredentialsNonExpired() { //니 비밀번호 안바꾼지 n년 지났니?
		return true; //아뇨?
	}

	
	@Override
	public boolean isEnabled() { //니 계정이 활성화 되어있니? 
		return true; //아뇨?
		//만약 회원이 1년동안 로그인 안했으면, 휴면계정으로 돌리고 싶다면,
		//User에 loginDate넣어놓고, 
		//if(현재시간 - user.getLoginDate() > 1year) { return false; }  
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collection;
	}


	
}
