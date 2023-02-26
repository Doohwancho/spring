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
        Optional<Employee> employee= repository.findByLastName(lastName);

        return employee.map(e -> String.format("Hello %s!", e.getLastName()))
                .orElse("Who is this "+lastName+" you're talking about?");
    }
}
