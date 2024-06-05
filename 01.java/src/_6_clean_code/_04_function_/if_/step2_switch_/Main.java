package _6_clean_code._04_function_.if_.step2_switch_;

public class Main {
  //Don't
  public String getOrderStatusMessage1(String status) {
    if (status.equals("processing")) {
      return "Your order is being processed.";
    } else if (status.equals("shipped")) {
      return "Your order has been shipped.";
    } else if (status.equals("delivered")) {
      return "Your order has been delivered.";
    } else if (status.equals("cancelled")) {
      return "Your order has been cancelled.";
    } else {
      return "Invalid status.";
    }
  }

  //Do
  public String getOrderStatusMessage2(String status) {
    switch (status) {
      case "processing":
        return "Your order is being processed.";
      case "shipped":
        return "Your order has been shipped.";
      case "delivered":
        return "Your order has been delivered.";
      case "cancelled":
        return "Your order has been cancelled.";
      default:
        return "Invalid status.";
    }
  }

}
