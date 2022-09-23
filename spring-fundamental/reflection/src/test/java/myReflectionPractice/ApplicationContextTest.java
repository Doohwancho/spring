package myReflectionPractice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationContextTest {

    @Test
    public void getObject_OrderRepository() throws Exception {
        OrderRepository object = ApplicationContext.getInstance(OrderRepository.class); //application context에서 OrderRepository 객체(빈) 가져오기
        assertNotNull(object);
    }

    @Test
    public void getObject_OrderService() throws Exception {
        OrderService orderService = ApplicationContext.getInstance(OrderService.class);
        assertNotNull(orderService);
        assertNotNull(orderService.orderRepository); //application context에서 꺼내온 빈에 주입된 다른 빈 가져오기.
        /*
        ---
        Q. 어떻게 reflection으로 @Autowired된 빈 객체 가져왔지?



        OrderService에서 보면,

        @Autowired
        OrderRepository orderRepository;

        이래 되있는데, @Autowired가 custom annotation일 뿐인데, 빈을 어케 가져옴?

        Autowired.interface 까보면,

        @Target(ElementType.FIELD)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface Autowired {
        }

        걍 런타임에 이 어노테이션 인지한다가 단데?
        안에 로직이 없는데?



         */
    }
}
