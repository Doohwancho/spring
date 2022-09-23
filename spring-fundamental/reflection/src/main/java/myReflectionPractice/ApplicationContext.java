package myReflectionPractice;

import java.lang.reflect.Field;

public class ApplicationContext {

    private final static Class[] EMPTY_PARAM = new Class[]{};

    public static <T> T getInstance(Class<T> clazz) throws Exception { //Class<T>에서 Class가 리플렉션. .class파일 말고 JVM에서 런타임에서 도는 생성된 모든 클래스를 관리해주는 애가 Class
        T instance = createInstance(clazz); //type을 T로 받고 리턴도 T로하는 이유는, OrderRepository object = ApplicationContext.getInstance(OrderRepository.class); 에서 OrderRepository.class로 받은 타입을 결국 고대로 리턴하기 때문.
        Field[] fields = clazz.getDeclaredFields();
        for(Field field: fields){ //이 필드가 Autowired.java에 @Target(ElementType.FIELD) 이거구나!
            if(field.getAnnotation(Autowired.class) != null){
                Object fieldInstance = createInstance(field.getType()); //@Autowired가 붙은 필드의 객체 생성.
                field.setAccessible(true); //그 객체의 생성자가 가령 private일 지라도, public으로 취급함.
                field.set(instance, fieldInstance); //OrderService.class 객체에 @Autowired붙은 필드인 orderRepository에 OrderRepository객체를 주입하는 것.
            }
        }
        return instance;
    }

    private static <T> T createInstance(Class<T> clazz) throws Exception {
        return clazz.getConstructor(EMPTY_PARAM).newInstance(); //reflection을 써서 해당 클래스의 빈 생성자, 기본생성자를 가지고 객체 생성하는 것
    }
}
