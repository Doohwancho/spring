package level4_Dispatcher_extends_HttpServlet.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import level4_Dispatcher_extends_HttpServlet.config.MessageConverter;
import level4_Dispatcher_extends_HttpServlet.config.ViewResolver;
import level4_Dispatcher_extends_HttpServlet.config.web.RequestMapping;
import level4_Dispatcher_extends_HttpServlet.domain.Member;
import level4_Dispatcher_extends_HttpServlet.util.UtilsLog;

import java.io.IOException;

public class MemberController {

    private static final String TAG = "MemberController : ";

    /*   GET     /join         */
    @RequestMapping("/join")
    public void join(Member member, HttpServletRequest request, HttpServletResponse response) {
        UtilsLog.getInstance().info(TAG, "join()");
        UtilsLog.getInstance().info(TAG, "Service가 호출되어 회원가입 완료되었습니다.");
        UtilsLog.getInstance().info(TAG, member.getUsername() +", "+member.getPassword());
        request.setAttribute("username", member.getUsername());
        ViewResolver.resolve("main.jsp", request, response);
    }

    /*   GET    /login         */
    @RequestMapping(value = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) {

        UtilsLog.getInstance().info(TAG, "login()");
        UtilsLog.getInstance().info(TAG, "Service가 호출되어 로그인이 완료되었습니다.");
        HttpSession session = request.getSession();
        session.setAttribute("principal", new Member(1, "ssar", "1234"));
        ViewResolver.resolve("main.jsp", request, response);
    }

    /*   GET    /findById         */
    @RequestMapping(value = "/findById")
    public void findById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UtilsLog.getInstance().info(TAG, "findById()");
        UtilsLog.getInstance().info(TAG, "Service가 호출되어 Member를 찾았습니다.");
        Member memberEntity = new Member(1, "ssar", "1234");
        MessageConverter.resolve(memberEntity, response);
    }


}
