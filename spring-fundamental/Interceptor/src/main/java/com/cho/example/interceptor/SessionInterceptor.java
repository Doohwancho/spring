package com.cho.example.interceptor;


import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

    private static final String LOGIN = "login";

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SessionInterceptor.class);


    //TODO - m-4. interceptor을 통한 간단한 인증 구현

    //컨트롤러보다 먼저 수행되는 메소드
    //http request에 "user" attribute가 없으면 로그인 페이지로 이동
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String requestURI = request.getRequestURI();

        log.info("로그인 인터셉터 실행: " + requestURI);

        HttpSession session = request.getSession();

        if(session == null || session.getAttribute("user") == null) {
            log.info("로그인 되지 않은 사용자 접근");
            response.sendRedirect("/user/loginForm");
            return false; //false면 해당 컨트롤러로 보내지 않는다.
        }

        return true; //true면 controller 호출, false면 바로 중단.
    }

//    @Override
    //Handler Adapter 호출 뒤에 호출되는 메서드
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("postHandle");
    }

    //TODO - m-5. afterCompletion()에서 예외 처리하기
    //view rendering 뒤에 호출되는 메서드
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        /**
         * 얘만 파라미터에 exception을 받는다. 왜지?
         *
         * 1. controller에서 에러 터지면, DispatcherServlet으로 예외 전달하고,
         * 2. 원래 다음으로 동작했어야 하는 HandlerAdapter는 호출이 안되고
         * 3. 바로 afterCompletion()이 실행되기 때문.
         */
        System.out.println("afterCompletion");
    }
}