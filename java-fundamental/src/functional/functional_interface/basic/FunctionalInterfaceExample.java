package functional.functional_interface.basic;

//추상메서드는 하나만 선언할 수 있습니다. 두 개 이상시 컴파일 에러가 발생합니다.
@FunctionalInterface
public interface FunctionalInterfaceExample {
    void printMsg(String msg);
    //void printName(String name);

    default void defaultMethod() {
        System.out.println("defalut Method");
    }

    static void staticMethod() {
        System.out.println("static Method");
    }
}