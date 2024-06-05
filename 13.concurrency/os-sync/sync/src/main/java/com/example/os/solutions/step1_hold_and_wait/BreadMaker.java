package main.java.com.example.os.solutions.step1_hold_and_wait;

public class BreadMaker extends Thread {
    private BreadPlate plate;    // 공유될 빵접시

    public BreadMaker(BreadPlate plate){
        this.plate = plate;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            plate.makeBread();
        }
    }
}
