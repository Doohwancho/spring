package level3_Dispatcher_using_filter.controller;

import level3_Dispatcher_using_filter.anno.Controller;
import level3_Dispatcher_using_filter.anno.RequestMapping;
import level3_Dispatcher_using_filter.dto.JoinDto;
import level3_Dispatcher_using_filter.dto.LoginDto;

@Controller
public class UserController {

    @RequestMapping(uri = "/user/join")
    public String join(JoinDto dto) {
        System.out.println("join 함수 요청됨");
        System.out.println(dto);
        return "/";
    }

    @RequestMapping(uri = "/user/login")
    public String login(LoginDto dto) {
        System.out.println("login 함수 요청됨");
        System.out.println(dto);
        return "/";
    }

    @RequestMapping(uri = "/user")
    public String user() {
        System.out.println("user 함수 요청됨");
        return "/";
    }
}
