package _9_effective_java._3장_모든객체의_공통메서드.__10.clone.what.step1;

public class Main {

  public static void main(String[] args)
  {
    // create an instance of Student
    Student s1 = new Student("Ashish", 121);

    //step1) Try to clone s1 and assign the new object to s2
//    Student s2 = s1.clone(); //error: incompatible types: Object cannot be converted to Student

    //custom degined Object를 .clone() 하며고 하면, deepcopy든 shallow copy든 안됨.
    //Q. 왜 안됨?

    //A. 자바언어가 너가 객체안에 뭘 만들 줄 알고, 복사해주는 메서드를 미리 만들겠어?
  }
}
