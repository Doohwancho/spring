package _6_clean_code._04_function_.setter;

public class Main {

  public static void main(String[] args) {

    //case1) don't ask, just tell - 의도 명확하게 드러내기

    Student student = new Student();

    //Don't
    student.setScore(80);

    //Do
    student.점수올리기(80);
    student.점수내리기(30);


    //case2) 의도치 않은 필드의 setter 방어
    student.setId(3000000); //@Setter 쓰면, 이것도 자동으로 만들어짐.


    //case3) custom Setter()는 validation check 로직 넣기 가능
    student.점수올리기(100000); //error!
  }

}
