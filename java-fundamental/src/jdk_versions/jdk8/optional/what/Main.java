package jdk_versions.jdk8.optional.what;

import java.util.Optional;

public class Main {

  public static void main(String[] args) {
    String name = "John Doe";

    //name이 null이 '절대' 아니면 Optional.of()으로 저장한다.
    Optional<String> optionalName = Optional.of(name);

    //name이 null일 수도 있으면, .ofNullable()로 저장한다.
    Optional<String> notSure = Optional.ofNullable(name);

    //.ofNullable()을 꺼내먹으려면?
    String result = notSure.orElse("null이면 이걸 넣어!");

    // Check if a value is present
    if (optionalName.isPresent()) {
      System.out.println("Name: " + optionalName.get());
    }

    // Conditional processing using ifPresent
    optionalName.ifPresent(n -> System.out.println("Name: " + n));

    // Conditional processing with a default value
    String defaultName = optionalName.orElse("Unknown");
    System.out.println("Name: " + defaultName);

    // Conditional processing with a supplier for default value
    String suppliedName = optionalName.orElseGet(() -> "Unknown");
    System.out.println("Name: " + suppliedName);

    // Conditional processing with an exception for empty value
    try {
      String retrievedName = optionalName.orElseThrow(() -> new RuntimeException("Name not found"));
      System.out.println("Name: " + retrievedName);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
  }

}
