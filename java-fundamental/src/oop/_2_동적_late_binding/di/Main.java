package oop._2_동적_late_binding.di;

public class Main {
  public static void main(String[] args) {
    MessageService service = new EmailService();
    MyApplication app = new MyApplication(service);
    app.processMessages("Hello World", "test@abc.com");
  }
}
