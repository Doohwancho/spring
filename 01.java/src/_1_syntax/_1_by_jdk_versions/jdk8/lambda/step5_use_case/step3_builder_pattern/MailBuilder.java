package _1_syntax._1_by_jdk_versions.jdk8.lambda.step5_use_case.step3_builder_pattern;

public class MailBuilder {
  private String from;
  private String to;
  private String subject;
  private String body;

  private MailBuilder() {}

  public static MailBuilder build(){
    return new MailBuilder();
  }

  public MailBuilder from(String address) {
    this.from = address;
    return this; //builder() 할 떄, 뒤로 넘겨줘야 하니까, return this
  }

  public MailBuilder to(String address) {
    this.to = address;
    return this;
  }

  public MailBuilder subject(String line) {
    this.subject = line;
    return this;
  }

  public MailBuilder body(String message) {
    this.body = message;
    return this;
  }

  public void send(){
    System.out.println("Sending mail: " + this);
  }

  @Override
  public String toString() {
    return "MailBuilder{" +
        "from='" + from + '\'' +
        ", to='" + to + '\'' +
        ", subject='" + subject + '\'' +
        ", body='" + body + '\'' +
        '}';
  }

}
