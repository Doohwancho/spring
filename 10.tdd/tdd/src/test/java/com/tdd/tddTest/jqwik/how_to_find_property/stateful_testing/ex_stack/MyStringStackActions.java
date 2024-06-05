package com.tdd.tddTest.jqwik.how_to_find_property.stateful_testing.ex_stack;

import java.io.Serializable;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.stateful.Action;
import org.assertj.core.api.Assertions;

class MyStringStackActions {


  static Arbitrary<Action<CustomStack>> actions() {
    return Arbitraries.oneOf(push(), clear(), pop()); //arbitrary that will randomly choose between all given
  }

  static Arbitrary<Action<CustomStack>> push() {
    return Arbitraries.strings().alpha().ofLength(5).map(PushAction::new);
  }

  private static Arbitrary<Action<CustomStack>> clear() {
    return Arbitraries.just(new ClearAction());
  }

  private static Arbitrary<Action<CustomStack>> pop() {
    return Arbitraries.just(new PopAction());
  }

  private static class PushAction implements Action<CustomStack>, Serializable {

    private final String element;

    private PushAction(String element) {
      this.element = element;
    }

    @Override
    public CustomStack run(CustomStack stack) {
      int sizeBefore = stack.size(); //before에서 체크해야 할 상태(stack.size())를 뽑고,
      stack.push(element);
      Assertions.assertThat(stack.isEmpty()).isFalse();
      Assertions.assertThat(stack.size()).isEqualTo(sizeBefore + 1); //상태가 바뀐 후, 검증한다.
      Assertions.assertThat(stack.top()).isEqualTo(element);
      return stack;
    }

    @Override
    public String toString() {
      return String.format("push(%s)", element);
    }
  }

  private static class ClearAction implements Action<CustomStack>, Serializable {

    @Override
    public CustomStack run(CustomStack stack) {
      stack.clear();
      Assertions.assertThat(stack.isEmpty()).isTrue();
      return stack;
    }

    @Override
    public String toString() {
      return "clear";
    }
  }

  private static class PopAction implements Action<CustomStack>, Serializable {

    @Override
    public boolean precondition(CustomStack stack) { //이전 상태에서 반드시 x해야 하는걸 여기서 처리함. null 처리라던가...
      return !stack.isEmpty();
    }

    @Override
    public CustomStack run(CustomStack stack) {
      //before 상태 변경
      int sizeBefore = stack.size();
      String topBefore = stack.top();

      //상태 변경!
      String popped = stack.pop();

      //after 상태 변경
      Assertions.assertThat(popped).isEqualTo(topBefore);
      Assertions.assertThat(stack.size()).isEqualTo(sizeBefore - 1);

      return stack;
    }

    @Override
    public String toString() {
      return "pop";
    }
  }
}