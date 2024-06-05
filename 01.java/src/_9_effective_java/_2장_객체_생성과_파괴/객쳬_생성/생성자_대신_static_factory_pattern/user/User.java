package _9_effective_java._2장_객체_생성과_파괴.객쳬_생성.생성자_대신_static_factory_pattern.user;

public class User {
	
	private String name;
	private int age;
	private String country;
	
	
	private User(String name, int age, String country) {
		this.name = name;
		this.age = age;
		this.country = country;
	}
	
	public static User KoreanUser(String name, int age) {
		return new User(name, age, "Korean");
	}
	
	
	private User() {
		
	}
	
	private static User instance = new User();
	
	public static User getInstance() {
		return instance;
	}
}
