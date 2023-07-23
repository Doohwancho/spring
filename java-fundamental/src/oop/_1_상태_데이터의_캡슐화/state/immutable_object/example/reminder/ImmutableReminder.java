package oop._1_상태_데이터의_캡슐화.state.immutable_object.example.reminder;

import java.util.Date;

public final class ImmutableReminder{
  private final Date remindingDate; //Date is mutable object.

  public ImmutableReminder (Date remindingDate) {
    if(remindingDate.getTime() < System.currentTimeMillis()){
      throw new IllegalArgumentException("Can not set reminder” + “ for past time: " + remindingDate);
    }
    this.remindingDate = new Date(remindingDate.getTime());
  }

  public Date getRemindingDate() {
    return (Date) remindingDate.clone();
  }
}