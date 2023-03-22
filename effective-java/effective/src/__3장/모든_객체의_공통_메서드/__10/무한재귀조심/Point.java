package __3장.모든_객체의_공통_메서드.__10.무한재귀조심;

abstract class Point {
    String color;

    boolean equals(Point p) {
        throw new UnsupportedOperationException("Unsupported equals");
    }
}
