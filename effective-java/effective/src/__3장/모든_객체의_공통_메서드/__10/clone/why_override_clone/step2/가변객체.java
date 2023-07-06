package __3장.모든_객체의_공통_메서드.__10.clone.why_override_clone.step2;

import __3장.모든_객체의_공통_메서드.__10.clone.why_override_clone.step3.가변_커스텀_객체의_커스텀_객체;

public class 가변객체 {
  public int value;
  public String name;

  public 가변객체(int value, String name){
    this.value = value;
    this.name = name;
  }

  @Override
  public String toString(){
    return "(value="+value+", name="+name + ")";
  }
}
