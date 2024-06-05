package _7_defensive_programming.null_handling.module_nullify.how;


import _7_defensive_programming.null_handling.module_nullify.util.Nullify;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NullHandlingTest {

  Integer addOne(Integer number){
    //before
//    if(number == null){
//      number = 0;
//    }
//    return number + 1;

    //after
    return Nullify.of(number, 0) + 1;
  }

  @Test
  void testWrapperClassWithNull() {
    //given
    Integer number1 = null;
    Integer number2 = 100;

    //when
    Integer result1 = addOne(number1);
    Integer result2 = addOne(number2);

    //then
    Assertions.assertEquals(result1, 1);
    Assertions.assertEquals(result2, 101);
  }

  List<Integer> addOne(List<Integer> list) {
    //before
//    if(list == null || list.size() == 0){
//      list = new ArrayList<>();
//    }
//
//    list.add(1);
//    return list;

    //after
    List<Integer> verifiedList = Nullify.of(list, new ArrayList<>());
    verifiedList.add(1);
    return verifiedList;
  }

  String addString(String str){
    //before
//    if(str == null){
//      str = new String();
//    }
//
//    return str + "!";

    //after
    return Nullify.of(str, "") + "!";
  }
  @Test
  void testStringWithNull() {
    //given
    String str1 = null;
    String str2 = "Hello World";

    //when
    String result1 = addString(str1);
    String result2 = addString(str2);

    //then
    Assertions.assertEquals(result1, "!");
    Assertions.assertEquals(result2, "Hello World!");
  }



  @Test
  void testListWithNull() {
    //given
    List<Integer> nullList = null;
    List<Integer> filledList = new ArrayList<>();
    filledList.add(100);

    //when
    List<Integer> result1 = addOne(nullList);
    List<Integer> result2 = addOne(filledList);

    //then
    Assertions.assertEquals(result1.get(result1.size()-1), 1);
    Assertions.assertEquals(result2.get(result2.size()-1), 1);
  }

}
