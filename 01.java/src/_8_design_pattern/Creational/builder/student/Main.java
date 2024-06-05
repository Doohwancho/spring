package _8_design_pattern.Creational.builder.student;

public class Main {

	public static void main(String[] args) {
		Student student = new Student.Builder("qjatjr1108", "1234")
                .name("bsjo")
                .address("Junghwa 1-dong, Jungnang-gu, Seoul, Korea")
                .phoneNumber("010-1234-1234")
                .build();
	}
}
