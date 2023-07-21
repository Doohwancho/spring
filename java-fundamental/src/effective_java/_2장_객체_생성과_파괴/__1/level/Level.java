package effective_java._2장_객체_생성과_파괴.__1.level;

public class Level {
	
	public static Level of(int score) {
		if (score < 50) {
			return new Basic();
		} else if (score < 80) {
			return new Intermediate();
		} else {
			return new Advanced();
		}
	}
}
