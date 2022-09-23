package level2_myReflectionPractice;

public class Application {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ApplicationContext();
        OrderService orderService = applicationContext.getInstance(OrderService.class);
    }
}
