package com.cho.example.databinding;

import com.cho.example.databinding.step1.EventEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

//    //case2)
//    @InitBinder
//    public void init(WebDataBinder webDataBinder){
//        webDataBinder.registerCustomEditor(Event.class, new EventEditor());
//        //Event 클래스 미리 등록해 놓으면,
//        //컨트롤러에 요청 닿기 전에 DataBinder에 들어있는 PropertyEditor 사용
//    }

    //case3)
    @Autowired
    ConversionService conversionService;

    @GetMapping("/event/{event}")
    public String getEvent(@PathVariable("event") Event event){
        return event.getId().toString();
    }
}
