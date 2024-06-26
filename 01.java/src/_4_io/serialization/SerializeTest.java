package _4_io.serialization;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializeTest {

	public static void main(String[] args) { 
		//생성한 객체를 직렬화하여 User.ser파일에 저장하는 예
		//저장하고 User.ser 파일 열어보면,
		//�� sr User$��0/> I ageL ableToSerializedt Ljava/lang/Object;L namet Ljava/lang/String;xp   t  이렇게 된
		//이렇게 써져있다. 
		
		try {
			String fileName = "User.ser";
			
			FileOutputStream fos = new FileOutputStream(fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream out = new ObjectOutputStream(bos);
			
			User u1 = new User("yesman","1234",30);
			User u2 = new User("yeswoman","4321",300);
			
			ArrayList<User> list = new ArrayList<>();
			
			list.add(u1);
			list.add(u2);
			
			// 객체를 직렬화 한다.
			out.writeObject(u1);
			out.writeObject(u2);
			out.writeObject(list);
			
			out.close();
			
			System.out.println("직렬화 끝!");
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}
}
