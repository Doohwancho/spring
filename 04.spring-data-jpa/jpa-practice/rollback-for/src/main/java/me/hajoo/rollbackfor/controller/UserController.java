package me.hajoo.rollbackfor.controller;

import lombok.RequiredArgsConstructor;
import me.hajoo.rollbackfor.dto.CreateUserVo;
import me.hajoo.rollbackfor.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /*
    curl -d '{"email":"test1", "password":"test1", "name":"test1"}' -H "Content-Type: application/json"  -X POST http://localhost:8080/signup -i
     */
    @PostMapping("/signup")
    public void signup(@RequestBody CreateUserVo createUserVo) {
        try {
            userService.signup(createUserVo);
        } catch (IOException | NullPointerException e) {}
    }

    @PostMapping("/signup-exception")
    public void signup_exception(@RequestBody CreateUserVo createUserVo) {
        try {
            userService.signup_rollbackFor_exception(createUserVo);
        } catch (IOException | NullPointerException e) {}
    }

    @GetMapping("/users/length")
    public int getUserLength() {
        return userService.find_users_length();
    }

}
