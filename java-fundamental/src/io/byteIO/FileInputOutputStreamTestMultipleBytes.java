package io.byteIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileInputOutputStreamTestMultipleBytes {
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream("src/io/FileInputOutputStreamTestMultipleBytes.java");
			fos = new FileOutputStream("file-output-stream.txt");
			
			int sizeOfByteRead = -1;
			//why -1?
			
			//아스키 코드가 숫자 1~255까지잖아?
			//여튼 양수잖아?
			//양수는 4byte에 가장 앞자리가 무조건 0이잖아?
			//근데 -1은 맨 앞자리가 1이잖아?(-1은 비트변환하면 1111111111111111)
			//그래서 파일 다 읽었는데 아스키 코드가 아니면(비어있다면) -1인듯?
			
			byte[] buffer = new byte[512];

			//한번 읽을 때 1바이트씩 읽는게 아니라, 버퍼사이즈 512 byte 정해주면, 512byte씩 배열에 담아온다.
						
			while((sizeOfByteRead = fis.read(buffer)) != -1) {
				//.read() returns next byte of data, or -1 if end of file has reached.
				fos.write(buffer, 0, sizeOfByteRead); //버퍼에 최대 512byte 담아온 걸 인덱스 0부터 sizeOfByteRead까지 읽어 덤핑
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close(); //free memory
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				fis.close(); //free memory
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("this process took "+ (endTime - startTime)+" mili-seconds"); // this process took 0 mili-seconds
		
		//Q. 왜 multiple bytes read가 single byte read보다 더 빠름?
		
		//A. 어짜피 os에 버퍼사이즈가 512바이트라, 1바이트씩 읽어줘! 해도, 512바이트 로드한 후, 1바이트만 채운 뒤, 고 1바이트 쓰는거라 비효율.
		//이 짓을, 파일에 쓰인 바이트의 갯수가 100개면, 100번 반복하는 것. 비효율.
		
		//반면, 버퍼사이즈를 512바이트로 해 놓았는데, 파일에 쓰인 바이트의 갯수가 100개면, 100개 한꺼번에 배열에 담고, FileOutputStream으로 배열 copy로 한큐에 다 털어서 더 빠른 것 
	}
}
