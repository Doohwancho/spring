package _9_effective_java._3장_모든객체의_공통메서드.__10.clone.why_override_clone.step3;

public class 가변_커스텀_객체 {
  public int value;
  public String name;
  public 가변_커스텀_객체의_커스텀_객체 object;

  public 가변_커스텀_객체(int value, String name, 가변_커스텀_객체의_커스텀_객체 object){
    this.value = value;
    this.name = name;
    this.object = object;
  }

  @Override
  public String toString(){
    return "(value="+value+", name="+name + ", object="+object.toString()+")";
  }

}
