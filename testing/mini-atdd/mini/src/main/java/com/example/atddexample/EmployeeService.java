package com.example.atddexample;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
class EmployeeService {
    EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public String greeting(String lastName) {
        Optional<Employee> target = repository.findByLastName(lastName);
        if (target.isEmpty()) {
            return "Who is this "+ lastName +" you're talking about?";
        }
        return "Hello " + lastName + "!";
    }
}
