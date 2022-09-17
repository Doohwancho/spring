package io.serialization;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DeserializeTest {
	
	public static void main(String[] args) { 
		try {
			String fileName = "User.ser";
			
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream in = new ObjectInputStream(bis);
			
			
			// 객체를 역직렬화 할 땐, 출력한 순서와 일치해야 한다.
			User u1 = (User)in.readObject();
			User u2 = (User)in.readObject();
			ArrayList list = (ArrayList)in.readObject();
			
			System.out.println(u1);
			System.out.println(u2);
			System.out.println(list);
			
			in.close();
			
			System.out.println("역 직렬화 끝!");
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}
}

/*
---
console.log



(yesman,null,30)
(yeswoman,null,300)
[(yesman,null,30), (yeswoman,null,300)]
역 직렬화 끝!



---
null?


password가 null인 이유는, transient 붙였기 때문!




*/
