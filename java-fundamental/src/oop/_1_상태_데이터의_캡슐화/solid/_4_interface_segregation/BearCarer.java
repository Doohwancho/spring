package oop._1_상태_데이터의_캡슐화.solid._4_interface_segregation;

public class BearCarer implements BearCleaner, BearFeeder { //주의! BearKeeper implement해서 안쓸 기능(bet bear)까지 받지 말고, 쓸 기능만 인터페이스 분리해서 받자!

    public void washTheBear() {
        //I think we missed a spot...
    }

    public void feedTheBear() {
        //Tuna Tuesdays...
    }
}
