package com.example.atddexample;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class EmployeeController {

    private AtomicLong id = new AtomicLong();
    private final EmployeeService service;

    @GetMapping("/hello/{lastName}")
    public GreetingDto greeting(@PathVariable String lastName) {
        String greeting = service.greeting(lastName);
        return createResult(greeting);
    }

    private GreetingDto createResult(String greeting) {
        GreetingDto greetingDto = new GreetingDto();
        greetingDto.setId(id.getAndIncrement());
        greetingDto.setMessage(greeting);
        return greetingDto;
    }
}
