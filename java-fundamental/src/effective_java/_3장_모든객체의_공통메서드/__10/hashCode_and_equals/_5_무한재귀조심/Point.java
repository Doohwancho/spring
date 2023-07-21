package effective_java._3장_모든객체의_공통메서드.__10.hashCode_and_equals._5_무한재귀조심;

abstract class Point {
    String color;

    boolean equals(Point p) {
        throw new UnsupportedOperationException("Unsupported equals");
    }
}
