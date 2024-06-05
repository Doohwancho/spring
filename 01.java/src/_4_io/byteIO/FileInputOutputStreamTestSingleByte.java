package _4_io.byteIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileInputOutputStreamTestSingleByte {
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream("src/io/FileInputOutputStreamTestSingleByte.java");
			fos = new FileOutputStream("file-output-stream.txt");
			
			int currentByte = -1;
			//why -1?
			
			//아스키 코드가 숫자 1~255까지잖아?
			//여튼 양수잖아?
			//양수는 4byte에 가장 앞자리가 무조건 0이잖아?
			//근데 -1은 맨 앞자리가 1이잖아?(-1은 비트변환하면 1111111111111111)
			//그래서 파일 다 읽었는데 아스키 코드가 아니면(비어있다면) -1인듯?
						
			while((currentByte = fis.read()) != -1) {
				//.read()는 1byte씩 읽어서 readData에 넣어준다. 
				//.read() returns next byte of data, or -1 if end of file has reached.
				fos.write(currentByte);  
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close(); //free _3_syntax_details.memory
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				fis.close(); //free _3_syntax_details.memory
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("this process took "+ (endTime - startTime)+" mili-seconds"); // this process took 5 mili-seconds

	}
}
