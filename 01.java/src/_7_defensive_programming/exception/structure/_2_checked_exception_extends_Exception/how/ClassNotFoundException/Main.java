package _7_defensive_programming.exception.structure._2_checked_exception_extends_Exception.how.ClassNotFoundException;

/*
    spring에서 reflection api써서 동적으로 클래스 할당받아 처리할 때,
    Class가 Runtime때 동적으로 로드가 안될 수도 있으니까,
    catch Exception 처리한다.
 */

public class Main {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("com.example.NonExistentClass");
            Object obj = cls.newInstance(); // Create an instance of the class
            // Use the class object (cls) and instance (obj) here
        } catch (ClassNotFoundException e) {
            System.out.println("The specified class was not found.");
            e.printStackTrace();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("An error occurred while creating an instance of the class.");
            e.printStackTrace();
        }
    }
}

