package com.tdd.tddTest.jqwik.syntax;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

public class MultipleConditionsTest {
  @Property
  boolean anyValidPersonHasAFullName(@ForAll("validPerson") Person aPerson) {
    return aPerson.fullName().length() >= 5;
  }
  @Provide
  Arbitrary<Person> validPerson() {
    Arbitrary<String> firstName = Arbitraries.strings()
        .withCharRange('a', 'z')
        .ofMinLength(2).ofMaxLength(10)
        .map(this::capitalize);
    Arbitrary<String> lastName = Arbitraries.strings()
        .withCharRange('a', 'z')
        .ofMinLength(2).ofMaxLength(20);
    return Combinators.combine(firstName, lastName).as(Person::new);
  }

  private String capitalize(String str) {
    return str.toUpperCase();
  }

  static class Person {
    private final String firstName, lastName;

    Person(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public String fullName() {
      return firstName + " " + lastName;
    }

    @Override
    public String toString() {
      return String.format("Person(%s:%s)", firstName, lastName);
    }
  }
}
