package _1_syntax._1_by_jdk_versions.jdk8.stream;

public class Student {
  private String name;
  private int score;
  private Sex sex;
  private City city;

  public Student(String name, int score, Sex sex) {
    this.name = name;
    this.score = score;
    this.sex = sex;
  }

  public Student(String name, int score, Sex sex, City city) {
    this.name = name;
    this.score = score;
    this.sex = sex;
    this.city = city;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public Sex getSex() {
    return sex;
  }

  public City getCity() {
    return city;
  }

  public enum Sex {MALE, FEMALE}

  public enum City {SEOUL, BUSAN}

  @Override
  public String toString() {
    return String.format("Student(name: %s, score: %d)", name, score);
  }
}
