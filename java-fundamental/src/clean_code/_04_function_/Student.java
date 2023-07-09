package clean_code._04_function_;

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
  public String getName(){
    return this.name;
  }


  //Do

  //Don't
  public void setName(String name){
    this.name = name;
  }

  //Do


  //Don't
  public void setScore(int score){
    this.score = score;
  }

  //Do
  public void 점수올리기(int score){
    this.score += score;
  }

}
