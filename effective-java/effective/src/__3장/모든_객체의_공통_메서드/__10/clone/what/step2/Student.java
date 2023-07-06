package __3장.모든_객체의_공통_메서드.__10.clone.what.step2;

public class Student implements Cloneable{
  String name = null;
  int id = 0;

  Student(String name, int id)
  {
    this.name = name;
    this.id = id;
  }

  @Override
  public Student clone() {
    try {
      return (Student) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError(); // Can't happen
    }
  }

  @Override
  public String toString() {
    return "Student [name=" + name + ", id=" + id + "]";
  }

}
