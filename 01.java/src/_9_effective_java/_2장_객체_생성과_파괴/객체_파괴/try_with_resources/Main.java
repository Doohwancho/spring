package _9_effective_java._2장_객체_생성과_파괴.객체_파괴.try_with_resources;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*

java에서는 finally에 close() 해야 하는 경우가 있다.
file && db 다룰 때..

1. InputStream
2. OutputStream
3. java.sql.Connection

instead of try~catch, use try-with-resources!
 */
public class Main {
	
	private static final int BUFFER_SIZE = 1024;
	
	static String firstLineOfFile(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
			return br.readLine();
		} finally {
			br.close(); //파일 하나면 try~ finally해서 닫는게 되는데,
		}
	}
	
	//case2) try~catch~finally
	static void copy2(String src, String dst) throws IOException {
		
        InputStream in = new FileInputStream(src);
        try {
			OutputStream out = new FileOutPutStream(dst);
			try {
				byte[] buf = new byte[BUFFER_SIZE];
				int n;
				while ((n = in.read(buf)) >= 0)
					out.write(buf, 0, n);
			} finally {
				out.close();
			}
		} finally {
			in .close(); //파일이 두개면, try~catch 쓰면, 반환하는 자원이 2개 이상이어서 nested하게 처리할 때, 문제 발생. nested안쪽 두번째 자원에서 에러나면, 첫번째 자원의 finally문 처리 안하는 등...
		}
	}
	
	
	//case3) try-with-resources
	static void copy3(String src, String dst) throws IOException {
	    try (InputStream in = new FileInputStream(src); OutputStream out = new FileOutPutStream(dst)) {
	        byte[] buf = new byte[BUFFER_SIZE];
	        int n;
	        while ((n = in .read(buf)) >= 0) out.write(buf, 0, n);
	    }
//		catch (IOException e) { //catch처리도 가능!
			// 처리
//		}
		//finally가 없어도 2개 이상 자원을 안전하게 반환 시켜준다!
	}
	
	private static class FileOutPutStream extends OutputStream {
		
		public FileOutPutStream(String dst) {
		}
		
		@Override
		public void write(int b) throws IOException {
		
		}
	}
}