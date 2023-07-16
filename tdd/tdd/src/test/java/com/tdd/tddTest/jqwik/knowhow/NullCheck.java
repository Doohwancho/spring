package com.tdd.tddTest.jqwik.knowhow;

import java.util.List;
import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

public class NullCheck {
  @Property
  void checkParamIstNotEmpty(@ForAll List<Integer> aList) {
    Assume.that(!aList.isEmpty()); //parameter가 비어있지 않음을 보중해 줌

    //pbt test code
  }

}
