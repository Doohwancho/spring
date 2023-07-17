package com.tdd.tddTest.jqwik.how_to_find_property.stateful_testing.ex_stack;


import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;
import net.jqwik.api.stateful.ActionSequence;

class TestCustomStack {

  MyStringStackActions actions;

  TestCustomStack(){
    actions = new MyStringStackActions();
  }

  @Property(tries = 10) @Report(Reporting.GENERATED)
  void checkMyStackMachine(@ForAll("sequences") ActionSequence<CustomStack> sequence) {
    sequence.run(new CustomStack());
  }

  @Provide
  Arbitrary<ActionSequence<CustomStack>> sequences() {
    return Arbitraries.sequences(MyStringStackActions.actions());
  }

  @Property @Report(Reporting.GENERATED)
  @Label("are equal after same sequence of pushes")
  boolean equality(@ForAll("pushes") ActionSequence<CustomStack> sequence) {
    CustomStack stack1 = sequence.run(new CustomStack());
    CustomStack stack2 = sequence.run(new CustomStack());
    return stack1.equals(stack2);
  }

  @Provide
  Arbitrary<ActionSequence<CustomStack>> pushes() {
    return Arbitraries.sequences(MyStringStackActions.push());
  }

}