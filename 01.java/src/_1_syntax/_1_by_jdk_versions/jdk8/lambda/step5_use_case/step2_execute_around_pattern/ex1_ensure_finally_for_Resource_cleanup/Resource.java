package _1_syntax._1_by_jdk_versions.jdk8.lambda.step5_use_case.step2_execute_around_pattern.ex1_ensure_finally_for_Resource_cleanup;

import java.util.function.Consumer;

public class Resource {
  private Resource() {
    System.out.println("Opening resource");
  }

  public void operate(Consumer<Resource> consumer) { //Consumer (T) -> void
    try {
      consumer.accept(this);
    } finally {
      close();
    }
  }

  public void close() {
    System.out.println("Cleaning up and closing resource");
  }

  public static void use(Consumer<Resource> consumer) {
    final Resource resource = new Resource();
    resource.operate(consumer);
  }
}
