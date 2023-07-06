package __3장.모든_객체의_공통_메서드.__10.hashCode_and_equals._4_identityHashCode;

public class Main {
  public static void main(String[] args) {
    String str1 = new String("abc");
    String str2 = new String("abc");

    System.out.println(str1.equals(str2)); //true 왜? 논리적 값이 같으니까.
    System.out.println(str1.hashCode()); //96354
    System.out.println(str2.hashCode()); //96354 (same) 왜? 논리적 값이 같으니까.

    System.out.println(System.identityHashCode(str1)); //1159190947
    System.out.println(System.identityHashCode(str2)); //925858445 (different) 왜? str1,str2의 논리적 값이 같아도 메모리 주소가 다르니까.

  }

}
