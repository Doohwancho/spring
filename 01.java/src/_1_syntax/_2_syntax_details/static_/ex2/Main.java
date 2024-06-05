package _1_syntax._2_syntax_details.static_.ex2;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("홍길동");
        p1.print_info(); //count:1
        
        p1.person_count = 10;
        
        Person p2 = new Person("김길동");
        p2.print_info(); //count:11
    }
}
