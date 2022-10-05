package com.cho.annotation.controller;

import com.cho.annotation.domain.Board;
import com.cho.annotation.domain.CommonDto;
import com.cho.annotation.domain.JoinReqDto;
import com.cho.annotation.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {


    //A-1) x-www-form-urlencoded
    @GetMapping("/user")
    public void x_www_form_urlencoded(String username, String password){

    }

    //A-2) plain/text (raw)
    @GetMapping("/user")
    public void text_plain_raw_type(@RequestBody String data){
        //parse data
    }

    //A-3) application/json
    @GetMapping("/user")
    public void json_application(@RequestBody User user){

    }


    //A-4) @PathVariable
    // http://localhost:8080/user/2
    @GetMapping("/user/{id}")
    public void findById(@PathVariable int id) { //@PathVariable은 저 url에서 {}로만 감싸면, {id}를 int로 파싱해서 넣어줌.
        System.out.println("findById() : id : "+id);
    }

    @CrossOrigin
    @GetMapping("/user")
    public void corsHandler(){

    }

    //A-5, A-6) @Valid
    @CrossOrigin
    @PostMapping("/user")
    public CommonDto<?> save(@Valid @RequestBody JoinReqDto dto, BindingResult bindingResult) {
//        userRepository.save(dto);
        return new CommonDto<>(HttpStatus.CREATED.value(), "ok");
    }

    //A-7) @RequestParam
    @GetMapping("/list")
    public ResponseEntity<List<Board>> requestParam(
            @RequestParam(value = "searchKeyWord1", required = false, defaultValue = "MangKyu") String searchKeyWord) {

        // searchKeyWord는 required가 false이기 때문에 없을 수도 있으므로, 없다면 기본값이 할당된다.
//        return ResponseEntity.ok(boardService.getBoardList(searchKeyWord1));
    }

    //A-8. @RequestBody
    @PostMapping("/requestBody")
    public ResponseEntity<Board> requestBody(@RequestBody Board board) {
        // @RequestBody는 MessageConverter를 통해 Json 형태의 HTTP Body를 Java 객체로 변환시킨다.
        return ResponseEntity.ok(board);
    }

    //A-9. @ModelAttribute
    @PostMapping("/modelAttribute")
    public ResponseEntity<Board> modelAttribute(@ModelAttribute Board board) {
        // @ModelAttribute는 폼(form) 형태의 HTTP Body와 요청 파라미터들을 객체에 바인딩시킨다.
        return ResponseEntity.ok(board);
    }

}
