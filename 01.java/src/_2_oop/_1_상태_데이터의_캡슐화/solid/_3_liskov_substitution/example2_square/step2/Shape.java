package _2_oop._1_상태_데이터의_캡슐화.solid._3_liskov_substitution.example2_square.step2;

public class Shape {
    protected int width;
    protected int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}
