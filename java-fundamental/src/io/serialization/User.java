package io.serialization;
import java.io.Serializable;

public class User implements Serializable{
	
	static final long serialVersionUID = 1L;
	//Q. why serial version UID?
	
	//A. 직렬화 -> 역직렬화 할 때, 클래스 이름 보고 맞는지 확인했는데,
	//클래스가 조금만 변경되어도 깨지니까, 버전까지 맞추는 것.
	
	
	String name;
	transient String password; //직렬화 대상에서 제외된다. 
	int age;
	
//	Object notAbleToSerialized = new Object(); //object는 직렬화 할 수 없다. 직렬화 하려고 하면, java.io.NotSerializableException 뜬다.
	Object ableToSerialized = new String("serializable"); //String은 직렬화 가능하다.
	
	public User() {
		this("cho","1234",50000);
	}

	public User(String name, String password, int age) {
		 this.name = name;
		 this.password = password;
		 this.age = age;
	}

	public String toString() {
		return "("+ name + "," + password + "," + age + ")";
	}
}
