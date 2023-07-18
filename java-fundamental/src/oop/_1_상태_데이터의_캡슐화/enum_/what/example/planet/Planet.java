package oop._1_상태_데이터의_캡슐화.enum_.what.example.planet;

public enum Planet {
  MERCURY(3.302e+23, 2.439e6),
  VENUS(4.879e+24, 6.052e6),
  EARTH(5.975e+24, 6.378e6);

  private final double mass;
  private final double radius;
  private final double surfaceGravity;

  // 중력상수(단위: m^3, kg s^2)
  private static final double G = 6.67300E-11;

  Planet(double mass, double radius) {
    this.mass = mass;
    this.radius = radius;
    surfaceGravity = G * mass / (radius * radius);
  }

  double mass() { return mass; }
  double radius() { return radius;}
  double surfaceGravity() { return surfaceGravity;}

  double surfaceGravity(double mass){
    return mass * surfaceGravity; //F = ma
  }
}
