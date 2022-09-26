package generics.generic_method;


public class Main {
	public static void main(String[] args) {
		Name<String> name = new Name<>();
		name.printClassName(1); //java.lang.Integer
		name.printClassName(1.2); //java.lang.Double
		
		Name<String> name2 = new Name<>();
		name2.printClassName(1); //에러가 안난다!
		name2.printClassName(1.2); //에러가 안난다!
		
		
		//왜?
		
		//클래스에 붙은 T랑 메서드에 붙은 T가 따로놀거든. 
	}
}
