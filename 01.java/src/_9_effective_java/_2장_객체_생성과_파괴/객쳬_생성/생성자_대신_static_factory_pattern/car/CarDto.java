package _9_effective_java._2장_객체_생성과_파괴.객쳬_생성.생성자_대신_static_factory_pattern.car;

public class CarDto {
	private String name;
	private int position;

	public CarDto(String name, int position) {
		this.name = name;
		this.position = position;
	}
	  
	public static CarDto from(Car car) {
		return new CarDto(car.getName(), car.getPosition());
	}
}