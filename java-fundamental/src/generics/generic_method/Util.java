package generics.generic_method;

public class Util {
	public static <T> Box<T> boxing(T t){
		//Q. 왜 그냥 public static Box<T> boxing(T t){} 하면 에러나지? 리턴타입 적어줬잖아?  
		//static이면 무조건 <T>도 붙여줘야 되는건가? 
		//-> 아님. static 빼도 Box<T> boxing(T t){} 하면 에러남. <T> 붙이라고.
		//왜지?
		//
		
		Box<T> box = new Box<T>();
		box.setT(t);
		return box;
	}
}
