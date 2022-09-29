package com.cho.annotation.controller;

import com.cho.annotation.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public class Controller {


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


    // http://localhost:8080/user/2
    @GetMapping("/user/{id}")
    public void findById(@PathVariable int id) { //@PathVariable은 저 url에서 {}로만 감싸면, {id}를 int로 파싱해서 넣어줌.
        System.out.println("findById() : id : "+id);
    }

    @CrossOrigin
    @GetMapping("/user")
    public void corsHandler(){

    }
}
