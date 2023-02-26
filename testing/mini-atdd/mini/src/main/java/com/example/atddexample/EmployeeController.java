package com.example.atddexample;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class EmployeeController {

    private final EmployeeService service;

    @GetMapping("/hello/{lastName}")
    public String greeting(@PathVariable String lastName) {
        return service.greeting(lastName);
    }
}
