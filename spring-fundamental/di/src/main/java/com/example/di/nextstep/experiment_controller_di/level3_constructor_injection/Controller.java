package com.example.di.nextstep.experiment_controller_di.level3_constructor_injection;

public class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    public void callService() {
        service.doSomething();
    }
}
