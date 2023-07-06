package access_modifier_.private_.ex1_singleton;

public class Singleton {
  private static Singleton instance; //static이라 heap으로 관리되지 않고 전역.

  private Singleton() { //생성자를 private으로 만들어서 객체생성도 안되고, 상속도 안되고.
  }

  public static Singleton getInstance() {
    if(instance == null) {
      instance = new Singleton();
    }
    return instance;
  }
}
