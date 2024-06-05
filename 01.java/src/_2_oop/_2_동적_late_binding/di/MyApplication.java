package _2_oop._2_동적_late_binding.di;


public class MyApplication {
  private MessageService service;

  public MyApplication(MessageService svc) {
    this.service = svc;
  }

  public void processMessages(String msg, String rec) {
    // do some msg validation, manipulation logic, etc.
    this.service.sendMessage(msg, rec);
  }
}
