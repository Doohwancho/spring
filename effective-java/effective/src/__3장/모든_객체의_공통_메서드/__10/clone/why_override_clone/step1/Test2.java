package __3장.모든_객체의_공통_메서드.__10.clone.why_override_clone.step1;

// Contains a reference of Test and implements
// clone with deep copy.
class Test2 implements Cloneable {
  int a, b;
  Test c; //Q. 만약 클론 떠야하는 객체 안에, 다른 객체의 reference가 있으면, 얜 어떻게 deepcopy로 클론 뜸?

  Test2(){
    a = 0;
    b = 0;
    c = new Test();
  }

  public Object clone() throws CloneNotSupportedException
  {
    // Assign the shallow copy to new reference variable
    // t
    Test2 t = (Test2)super.clone(); //필드는 걍 .clone()으로 되는데

    t.c = new Test(); //reference들은 @Override해서 수동으로 넣어줘야 하네?

    // Create a new object for the field c
    // and assign it to shallow copy obtained,
    // to make it a deep copy
    return t;
  }
}