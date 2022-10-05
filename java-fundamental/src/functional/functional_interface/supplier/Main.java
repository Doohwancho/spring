package functional.functional_interface.supplier;

public class Main {

    public static void main(String[] args) {
        Supplier<String> supplier= () -> "hello world";
        System.out.println(supplier.get()); //hello world

        Supplier<Student> studentSupplier = () -> new Student("조두환", 20);
        System.out.println(studentSupplier); //functional.functional_interface.supplier.Main$$Lambda$2/0x0000000800001210@2437c6dc
        System.out.println(studentSupplier.get()); //Student{name='조두환', age=20}
    }
}

/*

closure, lazy evaluation에서 쓰이는 것 처럼,


() -> ? 한 정보나 객체를 Supplier라는 외부 컨텍스트에 closure로 저장해놨다가, 
.get()으로 꺼내서 쓰는 개념인가보네 


*/
