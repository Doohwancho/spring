package _6_clean_code._04_function_.setter;

public class Student {
  private int id;
  private String name;
  private int score;

  Student(){}
  Student(int id, String name){
    this.id = id;
    this.name = name;
  }

  //Don't
  public void setId(int id){ //cons: @Setter 쓰면, 원하지 않는 id setter도 생김. 은닉 파괴!
    this.id = id;
  }

  //Don't
  public void setScore(int score){
    this.score = score;
  }

  //Do
  public void 점수올리기(int score){ //의도가 명확해짐
    if(this.score + score > 100){ //validation check 부여 가능
      throw new RuntimeException("100점을 초과하여 점수를 부과할 수 없습니다.");
    }
    this.score += score;
  }

  public void 점수내리기(int score){
    if(this.score - score < 0){ //validation check 부여 가능
      throw new RuntimeException("0점을 이하 점수를 부과할 수 없습니다.");
    }
    this.score -= score;
  }

}
