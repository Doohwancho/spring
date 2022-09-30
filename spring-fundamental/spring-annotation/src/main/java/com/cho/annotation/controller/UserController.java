package com.cho.annotation.controller;

import com.cho.annotation.domain.CommonDto;
import com.cho.annotation.domain.JoinReqDto;
import com.cho.annotation.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class UserController {


    //case1) x-www-form-urlencoded
    @GetMapping("/user")
    public void x_www_form_urlencoded(String username, String password){

    }

    //case2) plain/text (raw)
    @GetMapping("/user")
    public void text_plain_raw_type(@RequestBody String data){
        //parse data
    }

    //case3) application/json
    @GetMapping("/user")
    public void json_application(@RequestBody User user){

    }


    //case4) @PathVariable
    // http://localhost:8080/user/2
    @GetMapping("/user/{id}")
    public void findById(@PathVariable int id) { //@PathVariable은 저 url에서 {}로만 감싸면, {id}를 int로 파싱해서 넣어줌.
        System.out.println("findById() : id : "+id);
    }

    @CrossOrigin
    @GetMapping("/user")
    public void corsHandler(){

    }


    @CrossOrigin
    @PostMapping("/user")
    public CommonDto<?> save(@Valid @RequestBody JoinReqDto dto, BindingResult bindingResult) {
//        userRepository.save(dto);
        return new CommonDto<>(HttpStatus.CREATED.value(), "ok");
    }
}
