package clean_code._04_function_.if_.step1_why;

/*
    Q. 왜 if-else가 문제임?

    if (A) { ... }
    elseif (B) { ... }
    elseif (C) { ... }
    else (U\(A∪B∪C)) { ... }

    이건 사실..

    if (A) { ... }
    elseif (B\A) { ... }
    elseif ((C\B)\A) { ... }
    else (U\(A∪B∪C)) { ... }


    이거랑 같은 말인데, 읽기 너무 불편함.
    그러니까, if-else 구조를 읽기 편한 구조로 바꾸는게

    1. switch
    2. 역조건
 */
public class Main {
  //case1) if-else hell
  public String getAgeCategory(int age) {
    if (age < 5) {
      return "infant";
    } else {
      if (age < 12) {
        return "child";
      } else {
        if (age < 19) {
          return "teenager";
        } else {
          return "adult";
        }
      }
    }
  }


  //case2) early return. if문 안에 null 거르는 조건문 넣을 수도 있고, if문 대신 switch문 쓸 수도 있다.
  public String getAgeCategory2(int age) {
    if (age < 5) {
      return "infant";
    }
    if (age < 12) {
      return "child";
    }
    if (age < 19) {
      return "teenager";
    }
    return "adult";
  }

}

