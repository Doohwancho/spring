package org.zerock.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User{
	
	//serialVersionUID 왜 설정? 객체를 바이트배열로 변환해서 메모리에 저장하는데 이걸 직렬화라고 하고, 이걸 로드하는걸 역-직렬화라고 함. 
	//이때 serialVersionUID도 같이 저장되고, 로드될때 이게 다르면 exception error뜸. 클라이언트가 윈도운데 서버가 리눅스면, 
	//serialVersionUID가 JavaVM이 다르므로, 이 값이 다르게 설정되니까, 이걸 명시해주는게 좋다.
	private static final long serialVersionUID = 1L;  
	
	private MemberVO member;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(MemberVO vo) {
		super(vo.getUserid(), vo.getUserpw(), vo.getAuthList().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		
		this.member = vo;
	}
}
