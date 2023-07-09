package oop._2_동적_late_binding.composition;

public class Computer {
  private Processor processor;
  private RAM ram;

  public Computer() { //has-a relationship is composition
    this.processor = new Processor();
    this.ram = new RAM();
  }

  public void start() {
    this.processor.compute();
    this.ram.load();
  }
}