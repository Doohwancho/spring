package jdk_versions.jdk8.lambda.step5_use_case.step3_builder_pattern;

import java.util.function.Consumer;

public class MailMaker {
  private String from;
  private String to;
  private String subject;
  private String body;

  private MailMaker() {}

  public MailMaker from(String address) {
    this.from = address;
    return this; //builder() 할 떄, 뒤로 넘겨줘야 하니까, return this
  }

  public MailMaker to(String address) {
    this.to = address;
    return this;
  }

  public MailMaker subject(String line) {
    this.subject = line;
    return this;
  }

  public MailMaker body(String message) {
    this.body = message;
    return this;
  }

  public static void send(final Consumer<MailMaker> block) {
    final MailMaker mail = new MailMaker();
    block.accept(mail);
    System.out.println("Sending mail: " + mail);
  }

  @Override
  public String toString() {
    return "MailMaker{" +
        "from='" + from + '\'' +
        ", to='" + to + '\'' +
        ", subject='" + subject + '\'' +
        ", body='" + body + '\'' +
        '}';
  }
}
