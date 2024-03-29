package com.sp.sec2.controller;


import com.sp.sec2.domain.User;
import com.sp.sec2.service.UserService;
import com.sp.sec2.util.RestResponsePage;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // save
    @PostMapping("/save")
    public User saveUser(@RequestBody User user) throws DuplicateKeyException {
        return userService.save(user);
    }

    @GetMapping("/{userId}")
    public Optional<User> getUser(@PathVariable String userId){
        return userService.findUser(userId);
    }

    // list : page
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/list")
    public RestResponsePage<User> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        return RestResponsePage.of(userService.listUsers(page, size));
    }

    // add role
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
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

    // remove role
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/authority/remove")
    public Optional<User> removeAuthority(
            @RequestParam String userId,
            @RequestParam String authority
    ){
        userService.findUser(userId).ifPresent(user->{
            userService.removeAuthority(userId, authority);
        });
        return userService.findUser(userId);
    }

}