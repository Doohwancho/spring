package level5_ComponentScan;

import level3_Dispatcher_using_filter.controller.UserController;

public class SpringApplication {
    public static void main(String[] args) {
        // 1. 스프링이 모든 패키지의 클래스를 스캔함. (객체)
        ComponentScan componentScan = new ComponentScan();

        // 2. 패키지 이하의 모든 클래스를 컬렉션 담기

        // 3. 그 모든 클래스에서 특정 어노테이션이 걸려있는것을 다 찾아내요.
        UserController userController = componentScan.getObject(UserController.class);

        // 4. 그걸 IoC 등록

        // 5. @Autowired 동작함!!
        userController.join();
    }
}