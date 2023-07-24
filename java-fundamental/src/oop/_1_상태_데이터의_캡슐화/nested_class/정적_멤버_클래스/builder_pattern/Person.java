package oop._1_상태_데이터의_캡슐화.nested_class.정적_멤버_클래스.builder_pattern;

public class Person {
    private String name;
    private int age;
    private String address;
    
    private Person() {} //생성자를 private으로 만들어서 new를 방지하고,
    
    public static class Builder { //정적 멤버 클래스로 객체 생성
        private String name;
        private int age;
        private String address;
        
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder withAge(int age) {
            this.age = age;
            return this;
        }
        
        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }
        
        public Person build() {
            Person person = new Person();
            person.name = this.name;
            person.age = this.age;
            person.address = this.address;
            return person;
        }
    }
}
