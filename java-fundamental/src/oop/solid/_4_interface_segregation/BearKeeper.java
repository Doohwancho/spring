package oop.solid._4_interface_segregation;

public interface BearKeeper { //Don't! 기능 하나당 인터페이스 하나씩 쪼개자. 왜냐면, wash, feed 곰은 할 순 있는데 pet은 죽을수도 있으니까. 같이 붙이지 말고 분리해!
    void washTheBear();
    void feedTheBear();
    void petTheBear();
}
