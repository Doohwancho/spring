package _2_oop._1_상태_데이터의_캡슐화.solid._4_interface_segregation;

public class CrazyPerson implements BearPetter { //곰을 pet할 생각하는 미친 사람들만 implements BearPetter하고, 나머지 사람들은 받지 않으면 된다.

    public void petTheBear() {
        //Good luck with that!
    }
}
