package com.example.di.nextstep.experiment_controller_di.level3_constructor_injection;

import com.example.di.nextstep.experiment_controller_di.level2_setter_injection.Service;

public class ServiceImpl1 implements Service {
    @Override
    public void doSomething() {
        System.out.println("ServiceImpl1 is doing something");
    }
}
