package _2_oop._1_상태_데이터의_캡슐화.enum_.what.example.calculate;
public enum Operation2 {
  PLUS("+") {
    public double apply(double x, double y) { return x+y; }
  },
  MINUS("-") {
    public double apply(double x, double y) { return x-y; }
  },
  TIMES("*") {
    public double apply(double x, double y) { return x*y; }
  },
  DIVIDE("/") {
    public double apply(double x, double y) { return x/y; }
  };

  private final String symbol;

  Operation2(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return symbol;
  }

  public abstract double apply(double x, double y);
}
