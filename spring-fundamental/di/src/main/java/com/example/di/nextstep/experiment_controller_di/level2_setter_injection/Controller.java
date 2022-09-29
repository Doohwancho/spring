package com.example.di.nextstep.experiment_controller_di.level2_setter_injection;

public class Controller {
    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    public void callService() {
        service.doSomething();
    }
}
