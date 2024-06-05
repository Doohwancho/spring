package _7_defensive_programming.exception.how.구현방법론._1_실패원자적으로_만들기._1_불변객체;

public class OrderItem {
    private final String product;
    private final int quantity;
    
    public OrderItem(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    public String getProduct() {
        return product; // String은 불변 객체입니다.
    }
    
    public int getQuantity() {
        return quantity; // int는 원시 타입이므로 불변입니다.
    }
    
    // ... 다른 메서드들 ...
}
