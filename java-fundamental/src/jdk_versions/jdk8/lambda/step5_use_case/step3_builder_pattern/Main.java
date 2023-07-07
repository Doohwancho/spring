package jdk_versions.jdk8.lambda.step5_use_case.step3_builder_pattern;

public class Main {

  public static void main(String[] args) {
    //step1) create builder from scratch
    MailBuilder mailBuilder = MailBuilder.build().from("a@a.com")
        .to("b@b.com")
        .subject("without builder")
        .body("Wow it's handsome!");

    System.out.println(mailBuilder);

    System.out.println("================================");


    //step2) send()에 Consumer lambda 넣기.
    MailMaker.send(mail -> mail.from("a@a.com")
        .to("b@b.com")
        .subject("without builder")
        .body("Wow it's handsome!"));
  }
}
