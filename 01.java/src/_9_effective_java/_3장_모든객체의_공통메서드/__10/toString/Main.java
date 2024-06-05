package _9_effective_java._3장_모든객체의_공통메서드.__10.toString;

public class Main {

  public static void main(String[] args) {

    //Q. why @Override .toString()?
    //A. debugging & logging 시 편하라고.

    Person person = new Person("cho", 30);

    //before  (@Override .toString())
//    System.out.println(person.toString()); //__3장.모든_객체의_공통_메서드.__10.toString.Person@372f7a8d

    //after
    System.out.println(person.toString()); //Person{name='cho', age=30}
  }
}
