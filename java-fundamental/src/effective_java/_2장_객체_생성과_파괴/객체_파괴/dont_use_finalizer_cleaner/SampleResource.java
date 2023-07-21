package effective_java._2장_객체_생성과_파괴.객체_파괴.dont_use_finalizer_cleaner;

public class SampleResource implements AutoCloseable {
	@Override
	public void close() throws RuntimeException {
		System.out.println("close");
	}

	public void hello() {
		System.out.println("hello");
	}
}
