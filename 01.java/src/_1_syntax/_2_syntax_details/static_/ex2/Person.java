package _1_syntax._2_syntax_details.static_.ex2;

public class Person {
    static int person_count = 0;
    public int age = 0;
    public String name;
    
    Person(String param_name){
        this.name = param_name;
        person_count++;
        age++;
    }
    
    public void print_info() {
        System.out.println("인구"+person_count);
        System.out.println("name: "+this.name + "age: " + this.age);
    }
}
