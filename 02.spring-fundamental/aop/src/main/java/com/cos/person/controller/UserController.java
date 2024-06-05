package com.cos.person.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.person.dto.CommonDto;
import com.cos.person.dto.JoinReqDto;
import com.cos.person.dto.UpdateReqDto;
import com.cos.person.domain.User;
import com.cos.person.repository.UserRepository;

@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public CommonDto<List<User>> findAll() {
        System.out.println("findAll()");
        return new CommonDto<>(HttpStatus.OK.value(), userRepository.findAll()); //TODO - MessageConverter (JavaObject -> Json String)
    }

    /*
        http response in json

        {
        "statuscode": 200,
        "data": {
            "id":3,
            "username":"ssar",
            "password":"1234",
            "phone":"010-123-123"
        },
        "data": {
            "id":4,
            "username":"ssar2",
            "password":"1234234",
            "phone":"010-123-123234"
        },
        //...
    }
     */

    @GetMapping("/user/{id}")
    public CommonDto<User> findById(@PathVariable int id) { //TODO - @PathVariable은 저 url에서 {}로만 감싸면, {id}를 int로 파싱해서 넣어줌.
        System.out.println("findById() : id : "+id);
        return new CommonDto<>(HttpStatus.OK.value(), userRepository.findById(id));
    }

    @CrossOrigin
    @PostMapping("/user")
    public CommonDto<?> save(@Valid @RequestBody JoinReqDto dto, BindingResult bindingResult) {
        userRepository.save(dto);
        return new CommonDto<>(HttpStatus.CREATED.value(), "ok");
    }

    @DeleteMapping("/user/{id}")
    public CommonDto delete(@PathVariable int id) {
        userRepository.delete(id);
        return new CommonDto<>(HttpStatus.OK.value());
    }

    @PutMapping("/user/{id}")
    public CommonDto update(@PathVariable int id, @Valid @RequestBody UpdateReqDto dto, BindingResult bindingResult) {
        userRepository.update(id, dto);
        return new CommonDto<>(HttpStatus.OK.value());
    }
}

