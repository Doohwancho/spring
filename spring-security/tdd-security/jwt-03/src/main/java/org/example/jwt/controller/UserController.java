package org.example.jwt.controller;

import lombok.AllArgsConstructor;
import org.example.jwt.domain.User;
import org.example.jwt.service.UserService;
import org.example.jwt.util.RestResponsePage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public Optional<User> getUser(@PathVariable String userId){
        return userService.findUser(userId);
    }

//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Secured("ROLE_ADMIN")
    @GetMapping("/list")
    public RestResponsePage<User> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        return RestResponsePage.of(userService.listUsers(page, size));
    }

//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Secured("ROLE_ADMIN")
    @PutMapping("/authority/add")
    public Optional<User> addAuthority(
            @RequestParam String userId,
            @RequestParam String authority
    ){
        userService.findUser(userId).ifPresent(user->{
            userService.addAuthority(userId, authority);
        });
        return userService.findUser(userId);
    }
}
