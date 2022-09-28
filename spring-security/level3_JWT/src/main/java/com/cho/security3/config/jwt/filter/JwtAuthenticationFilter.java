package com.cho.security3.config.jwt.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.cho.security3.config.auth.PrincipalDetails;
import com.cho.security3.config.jwt.JwtProperties;
import com.cho.security3.dto.LoginRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


/*
 * 클라이언트가 로그인에성공했을 때, jwt token 생성해서 보내주는 곳. 
 * 
 * SecurityConfig.java에서 .formLogin().disable() 해버였기 때문에, 
 * PrincipalDetailsService가 작동을 안하니, 얘를 수동으로 실행시켜주기 위해 쓰는 필터. 
 * 
 * */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	
	private final AuthenticationManager authenticationManager; //SecurityConfig.java에 .addFilter(new JwtAuthenticationFilter(authenticationManager()))  에 쓰임. 
	
	// Authentication 객체 만들어서 리턴 => 의존 : AuthenticationManager
	// 인증 요청시에 실행되는 함수 => /login
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		System.out.println("JwtAuthenticationFilter : 진입");
		
		// 1. request에 있는 username과 password를 파싱해서 자바 Object로 받기
		ObjectMapper om = new ObjectMapper(); //ObjectMapper은 json 데이터 파싱해주는 놈 
		LoginRequestDto loginRequestDto = null;
		try {
			loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDto.class); //들어온 json 데이터를 유저 Dto에 담아줌. 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("JwtAuthenticationFilter : "+loginRequestDto);
		
		// 2. 유저네임패스워드 토큰 생성
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(
						loginRequestDto.getUsername(), 
						loginRequestDto.getPassword());
		
		System.out.println("JwtAuthenticationFilter : 토큰생성완료");
		
		// 3. 저 유저 정보가 정상인지 로그인 시도 해보는 것
		
		// authenticationManager.authenticate() 함수가 호출 되면, 
		// 인증 프로바이더가 UserDetailsService를 받는 PrincipalDetailsService.loadUserByUsername(토큰의 첫번째 파라메터) 를 호출하고
		// UserDetails를 리턴받아서 토큰의 두번째 파라메터(credential)과
		// UserDetails(DB값)의 getPassword()함수로 비교해서 동일하면
		// spring security session에 저 유저 정보를 UserDetails(Principal)로 저장하고, 
		// Authentication 객체를 만들어서 필터체인으로 리턴해준다.
		// 즉, 인증 해준다는 말이네? 
		
		// Tip: 인증 프로바이더의 디폴트 서비스는 UserDetailsService 타입
		// Tip: 인증 프로바이더의 디폴트 암호화 방식은 BCryptPasswordEncoder
		// 결론은 인증 프로바이더에게 알려줄 필요가 없음.
		Authentication authentication = 
				authenticationManager.authenticate(authenticationToken);
		
		//값 확인해보기 
//		PrincipalDetails principalDetailis = (PrincipalDetails) authentication.getPrincipal();
//		System.out.println("Authentication : "+principalDetailis.getUser().getUsername());
		
		
		return authentication; //이렇게 리턴하는건, spring security session으로 관리하면 권한처리 기능을 사용할 수 있기 때문.  
	}

	
	//attemptAuthentication()가 실행된 후, successfulAuthentication()가 실행된다. 
	// 여기서 JWT Token 생성해서 response에 담아 client에게 주기
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		PrincipalDetails principalDetailis = (PrincipalDetails) authResult.getPrincipal();
		
		String jwtToken = JWT.create()
				.withSubject(principalDetailis.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
				.withClaim("id", principalDetailis.getUser().getId())
				.withClaim("username", principalDetailis.getUser().getUsername())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken); //HttpResponseHeader에 "{Authorization":"Bearer ${jwtToken}"} 해서 보내는 군. 
	}
	
}