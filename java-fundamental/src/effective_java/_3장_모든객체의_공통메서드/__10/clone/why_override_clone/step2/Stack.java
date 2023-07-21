package effective_java._3장_모든객체의_공통메서드.__10.clone.why_override_clone.step2;

public class Stack implements Cloneable{
  private Object[] elements;
  private int size;


  public Stack(int capacity){
    elements = new Object[capacity];
    size = 0;
  }

  public void push(Object e){
    elements[size++] = e;
  }

  public Object pop(){
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
  public Stack clone(){
    try{
      Stack result = (Stack)super.clone();

      //case2) 가변 객체(Object)는 별도로 .clone()처리 한다.
      result.elements = elements.clone(); //이게 주석처리 되있으면 shallow copy 한다.

      //Q. 근데 만약에 저 가변 객체가 String, Integer 등이 아니라, 또다른 custom 객체라면?

      return result;
    }catch (CloneNotSupportedException e){
      throw new AssertionError();
    }
  }
}


