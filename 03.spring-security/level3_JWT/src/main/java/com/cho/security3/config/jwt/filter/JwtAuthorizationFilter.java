package com.cho.security3.config.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.cho.security3.config.auth.PrincipalDetails;
import com.cho.security3.config.jwt.JwtProperties;
import com.cho.security3.model.User;
import com.cho.security3.repository.UserRepository;


/*
 * 스프링 시큐리티 필터에서, 권한 || 인증 필요한 페이지 접근하면, 무조건 BasicAuthenticationFilter를 거쳐야 함.
 * ROLE_USER 이런 애들 있잖아. 
 * 
 * 이 필터를 통해 이 client 권한 있다라고 증명하는 것 
 */

// 인가
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{
	
	private UserRepository userRepository;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}
	
	//JwtAuthenticationFilter에서 보낸 Bearer ${jwt token} 보낸걸, 
	//유저가 ROLE_MANAGER 같은 권한 필요한 페이지에 HttpRequest 요청시, 헤더에 Authorization: 위에 Bearer jwt 담아 보내는데,
	//저 Authorization 헤더에 jwt 토큰을 가져다가 읽어서, 해당유저가 맞고 권한있다고 인증하는 것. 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader(JwtProperties.HEADER_STRING);
		
		if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
                        return;
		}
		System.out.println("header : "+header);
		String token = request.getHeader(JwtProperties.HEADER_STRING)
				.replace(JwtProperties.TOKEN_PREFIX, "");
		
		// 토큰 검증 (이게 인증이기 때문에 AuthenticationManager도 필요 없음)
		// 내가 SecurityContext에 집적접근해서 세션을 만들때 자동으로 UserDetailsService에 있는 loadByUsername이 호출됨.
		String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
				.getClaim("username").asString();
		
		if(username != null) {	
			User user = userRepository.findByUsername(username);
			
			// 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행해주는 권한 처리를 위해 
			// 아래와 같이 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 스프링 시큐리티 세션에 저장!
			// 왜? PrincipalDetails를 시큐리티 컨텍스트에 안담으면, 권한관리(특정 페이지는 ROLE_USER만 들어오고, ROLE_MANAGER만 들어오고 등..) 가 안되기 때문.
			PrincipalDetails principalDetails = new PrincipalDetails(user);
			Authentication authentication =
					new UsernamePasswordAuthenticationToken(
							principalDetails, //나중에 컨트롤러에서 DI해서 쓸 때 사용하기 편함.
							null, // 패스워드는 모르니까 null 처리, 어차피 지금 인증하는게 아니니까!!
							principalDetails.getAuthorities());
			
			// 강제로 시큐리티의 세션에 접근하여 값 저장
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	
		chain.doFilter(request, response);
	}
	
}

