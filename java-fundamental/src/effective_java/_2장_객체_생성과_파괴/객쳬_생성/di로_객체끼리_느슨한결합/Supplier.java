package effective_java._2장_객체_생성과_파괴.객쳬_생성.di로_객체끼리_느슨한결합;

@FunctionalInterface
public interface Supplier<T> {
		/**
     * Gets a result.
     *
     * @return a result
     */
    T get(); // T 타입 객체를 찍어낸다
    
//    Mosaic create(Supplier<? extends Tile> tileFactory) { ... }
}
