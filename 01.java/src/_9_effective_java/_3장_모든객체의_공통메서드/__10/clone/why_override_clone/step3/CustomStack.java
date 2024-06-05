package _9_effective_java._3장_모든객체의_공통메서드.__10.clone.why_override_clone.step3;

public class CustomStack implements Cloneable{
  private 가변_커스텀_객체[] elements;
  private int size;


  public CustomStack(int capacity){
    elements = new 가변_커스텀_객체[capacity];
    size = 0;
  }

  public void push(가변_커스텀_객체 e){
    elements[size++] = e;
  }

  public 가변_커스텀_객체 pop(){
    return elements[--size];
  }

  public int getSize(){
    return size;
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for(int i = 0; i < size; i++){
      sb.append(elements[i].toString());
      if(i < size - 1)
        sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }

  @Override
  public CustomStack clone(){
    try{
      CustomStack result = (CustomStack)super.clone();

      //Q. 왜 step2랑은 다르게, custom class는 아래 코드 안해도 deep copy됨? shallow copy 되어야 하는거 아님?
//      result.elements = elements.clone();

      return result;
    }catch (CloneNotSupportedException e){
      throw new AssertionError();
    }
  }
}


