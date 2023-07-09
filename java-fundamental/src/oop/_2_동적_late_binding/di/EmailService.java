package oop._2_동적_late_binding.di;

public class EmailService implements MessageService {
  @Override
  public void sendMessage(String msg, String rec) {
    // logic to send email
    System.out.println("Email sent to " + rec + " with Message=" + msg);
  }
}