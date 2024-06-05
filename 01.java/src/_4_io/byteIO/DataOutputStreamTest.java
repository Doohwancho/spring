package _4_io.byteIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataOutputStreamTest {
	public static void main(String[] args) {
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream("data-out-stream.txt"));
			//DataOutputStream은 여러 종류(타입)의 intput stream, output stream을 받을 수 있게 만든 데코레이터 
			
			//out. 하면, 여러 타입에 대한 write()가 뜬다. 
			out.writeInt(100); //int니까 4byte 쓰고 
			out.writeBoolean(true); //boolean이니까 1byte쓰고 
			out.writeDouble(50.5); //double이니까 8byte 쓰고 
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			DataInputStream in = new DataInputStream(new FileInputStream("data-out-stream.txt"));
			
			in.readInt();
			in.readBoolean();
			in.readDouble();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
