package com.cho.security2.controller;


import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cho.security2.config.auth.PrincipalDetails;
import com.cho.security2.model.User;
import com.cho.security2.repository.UserRepository;


@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping({ "", "/" })
	public @ResponseBody String index() {
		return "인덱스 페이지입니다.";
	}

	@GetMapping("/user")
	public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principal) {
		System.out.println("Principal : " + principal);
		System.out.println("OAuth2 : "+principal.getUser().getProvider());
		// iterator 순차 출력 해보기
		Iterator<? extends GrantedAuthority> iter = principal.getAuthorities().iterator();
		while (iter.hasNext()) {
			GrantedAuthority auth = iter.next();
			System.out.println(auth.getAuthority());
		}

		return "유저 페이지입니다.";
	}

	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "어드민 페이지입니다.";
	}
	
	//@PostAuthorize("hasRole('ROLE_MANAGER')")
	//@PreAuthorize("hasRole('ROLE_MANAGER')")
	@Secured("ROLE_MANAGER")
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "매니저 페이지입니다.";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/join")
	public String join() {
		return "join";
	}

	@PostMapping("/joinProc")
	public String joinProc(User user) {
		System.out.println("회원가입 진행 : " + user);
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");
		userRepository.save(user);
		return "redirect:/";
	}
	
	//PrincipalDetails은 구글 회원 정보 보여주고, 
	//UserDetails은 디비에 회원가입한 유저 정보 보여주네?
	@GetMapping("/test/login")
	public @ResponseBody String testLogin(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principal, @AuthenticationPrincipal UserDetails userDetails, @AuthenticationPrincipal OAuth2User oauth) {
		
		System.out.println("/test/login");
		
		//case1) PrincipalDetails for google user info
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal(); //PrincipalDetails로 다운캐스팅 
		System.out.println("authentication: "+principalDetails.getUser()); 
		//authentication: User(id=2, username=google_115754241141809874618, password=null, email=doohwancho1993@gmail.com, role=ROLE_USER, provider=google, providerId=115754241141809874618, createDate=2022-09-25 05:30:19.525)
		//PrincipalDetails는 구글 정보가 뜨네? 
		
		//case2) UserDetails for User info from database
		System.out.println(userDetails.toString()); 
		//PrincipalDetails(user=User(id=1, username=id, password=$2a$10$uQCajMsRSb6PxGPr/ojFqemIoGF.uIB0DT9yW1qT9oFcu38zI3fJO, email=pw@pw.com, role=ROLE_USER, provider=null, providerId=null, createDate=2022-09-25 05:23:51.386), attributes=null)
		//userDetails는 구글 정보 말고 회원가입 했을 때 정보가 뜬다.  
		
		//case3) 
		OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal(); //OAuth2User로 다운캐스팅 
		System.out.println(oauth2User.getAttributes());  
		//만약에 구글로 로그인 안하고 회원가입으로 가입한 아이디로만 로그인하면, null 뜸.
		//만약에 구글로 로그인 했다면, 아래와 같이 뜸.  {sub=115754241141809874618, name=plain normal, given_name=plain, family_name=normal, picture=https://lh3.googleusercontent.com/a/ALm5wu00Mzig6PfAPXav9GZEy3wuSskEJfsIEH6VFum9=s96-c, email=doohwancho1993@gmail.com, email_verified=true, locale=en}
		//PrincipalOauth2UserService.loadUser() 에서 OAuth2User oAuth2User = super.loadUser(userRequest); 에서 받은 정보. 
		
		//case4)
		System.out.println(oauth.getAttributes()); 
		//만약에 구글로 로그인 안하고 회원가입으로 가입한 아이디로만 로그인하면, null 뜸.
		//만약에 구글로 로그인 했다면, 아래와 같이 뜸. {sub=115754241141809874618, name=plain normal, given_name=plain, family_name=normal, picture=https://lh3.googleusercontent.com/a/ALm5wu00Mzig6PfAPXav9GZEy3wuSskEJfsIEH6VFum9=s96-c, email=doohwancho1993@gmail.com, email_verified=true, locale=en}
		
		return "세션 정보 확인하기";
	}
}
