package effective_java._2장_객체_생성과_파괴.__8;

public class SampleResource implements AutoCloseable {
	@Override
	public void close() throws RuntimeException {
		System.out.println("close");
	}

	public void hello() {
		System.out.println("hello");
	}
}
