package com.sp.sec.controller;

import com.sp.sec.dto.SecurityMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member")
@RestController
public class MemberController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(defaultValue = "false") Boolean error,
                            Model model) {
        if (error) {
            model.addAttribute("errorMessage", "아이디나 패스워드가 올바르지 않습니다.");
        }
        return "loginForm";
    }

    //TODO - j-a-1
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(value = "/user")
    public SecurityMessage user() {
        return SecurityMessage.builder()
                .message("user page")
                .auth(SecurityContextHolder.getContext().getAuthentication()).build();
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping(value = "/admin")
    public SecurityMessage admin() {
        return SecurityMessage.builder()
                .message("admin page")
                .auth(SecurityContextHolder.getContext().getAuthentication()).build();
    }
}
