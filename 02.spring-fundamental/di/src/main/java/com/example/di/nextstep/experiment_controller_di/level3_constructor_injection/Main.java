package com.example.di.nextstep.experiment_controller_di.level3_constructor_injection;

public class Main {
    public static void main(String[] args) {

//         Controller controller = new Controller(); // 컴파일 에러. 왜? Service DI 안받았기 때문.

        Controller controller1 = new Controller((Service) new ServiceImpl1());
        Controller controller2 = new Controller(
                () -> System.out.println("Lambda implementation is doing something")
        );
        Controller controller3 = new Controller(new Service() {
            @Override
            public void doSomething() {
                System.out.println("Anonymous class is doing something");
            }
        });

        controller1.callService();
        controller2.callService();
        controller3.callService();
    }
}
