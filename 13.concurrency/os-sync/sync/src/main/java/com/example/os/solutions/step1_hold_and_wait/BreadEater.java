package main.java.com.example.os.solutions.step1_hold_and_wait;


public class BreadEater extends Thread {
    private BreadPlate plate;

    public BreadEater(BreadPlate plate){
        this.plate = plate;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            plate.eatBread();
        }
    }
}
