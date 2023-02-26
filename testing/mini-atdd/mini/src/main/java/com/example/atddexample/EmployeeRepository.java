package com.example.atddexample;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Repository
class EmployeeRepository {
    Map<Long, String> database = new HashMap<>();
    Long sequence = 0L;

    public Optional<Employee> findByLastName(String lastName) {
        for (Map.Entry<Long, String> entry : database.entrySet()) {
            if (entry.getValue().equals(lastName)) {
                return Optional.of(new Employee(entry.getValue()));
            }
        }
        return null;
    }

    public void add(Employee targetEmployee) {
        database.put(++sequence, targetEmployee.lastName);
    }
}
