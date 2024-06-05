package com.example.appkata.module.login.api;


import com.example.appkata.module.login.application.dto.LoginRequest;
import com.example.appkata.module.login.application.dto.LoginResponse;
import com.example.appkata.module.login.application.LoginService;
import com.example.appkata.module.login.application.dto.LoginSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginApi {

    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        LoginSession user = loginService.login(request);
        return new LoginResponse(user.getEmail(), user.getUsername());
    }
}