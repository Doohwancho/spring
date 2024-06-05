package _9_effective_java._3장_모든객체의_공통메서드.__10.hashCode_and_equals._4_identityHashCode;

public class Main {
  public static void main(String[] args) {
    String str1 = new String("abc");
    String str2 = new String("abc");

    System.out.println(str1.equals(str2)); //true 왜? 논리적 값이 같으니까.
    System.out.println(str1.hashCode()); //96354
    System.out.println(str2.hashCode()); //96354 (same) 왜? 논리적 값이 같으니까.

    System.out.println(System.identityHashCode(str1)); //1159190947
    System.out.println(System.identityHashCode(str2)); //925858445 (different) 왜? str1,str2의 논리적 값이 같아도 메모리 주소가 다르니까.

    //Q. what is identityHashCode()?
    //
    // hashCode()는 객체의 메모리 주소가 같야 아니냐 비교하기 위해 생긴게 아니라,
    // 해당 객체의 상태값, 논리적으로 값이 같냐 다르냐를 위해 만들어진 놈.

    // 반면 identityHashCode()는 논리적인 일치 상관 없이, 그 객체가 unique하냐 안하냐를 위해 만들어진 놈.
    // 보통 identityHashCode()를 계산할 때, uniqueness 보장위해서, 해당 객체의 메모리 주솟값이 들어가긴 하나,
    // 100% 약속은 아니고 jvm implementation에 따라 달라짐.


    //Q. when to use?
    // 여튼 객체가 unique하냐 아니냐 따질 때 identityHashCode()를 사용한다.

  }

}
