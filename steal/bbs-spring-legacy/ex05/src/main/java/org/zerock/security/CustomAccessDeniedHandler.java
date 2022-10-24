package org.zerock.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
	
	/* 접근 제한된 경우 다양한 처리를 하고 싶다면, 직접 AccessDeniedHandler 인터페이스 구현하는게 좋음.
	 * 
	 * 예를들어, 접근 제한이 되었을 때, 쿠키나 세션에 특정한 작업을 하거나, 
	 * 
	 * HttpServletResponse에 특정한 헤더 정보를 추가하는 등의 행위를 할 경우, 직접 구현하는 방식을 더 권장
	 * 
	 */
	
	@Override
	public void handle(HttpServletRequest request, 
					   HttpServletResponse response,
					   AccessDeniedException accessException)
				throws IOException, ServletException{
		
		log.error("Access Denied Handler!");
		
		log.error("Redirect......");
		
		log.info(accessException);
		
		response.sendRedirect("/accessError");
	}

}
