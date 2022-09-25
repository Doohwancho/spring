package __2.객체_생성과_파괴.__1;

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