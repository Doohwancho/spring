package com.example.di.nextstep.experiment_controller_di.level1_field_injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController_FieldInjection {

    //case1) @Autowired - 옛날 방식. 더 이상 안씀.
    @Autowired
    private UserService userService;
}
