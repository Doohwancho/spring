package com.cho.annotation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userRest")
@RequiredArgsConstructor
public class UserRestController {
//    private final UserService userService;

//    @GetMapping(value = "/users")
//    public User findUser(@RequestParam("userName") String userName){
//        return userService.findUser(user);
//    }

//    @GetMapping(value = "/users")
//    public ResponseEntity<User> findUserWithResponseEntity(@RequestParam("userName") String userName){
//        return ResponseEntity.ok(userService.findUser(user));
//    }
}
/*

---
Controller vs RestController


Controller는 service에 받아온 모델을 view resolver랑 짝짝꿍해서 반환이 일반적.
반면 RestController는 viewResolver 안거치고 모델을 바로 ResponseEntity에 담아서 json형태든 뭐든 client로 바로 반환함.

그래서 반환타입보면 RestController는 User, ResponseEntity<User> 이런 애들이 많고,
Controller는 view 반환해야 하니까 주로 String임.
물론 데이터 반환해야 하는 경우 RestController처럼 @ResponseBody ResponseEntity<User> 반환하는 식도 가능.

 */
