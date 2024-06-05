package _2_oop._2_동적_late_binding.composition;

public class Main {
  public static void main(String[] args) {
    Computer computer = new Computer();
    computer.start();
    // Outputs "Processor is computing..."
    // Outputs "RAM is loading data..."
  }
}
