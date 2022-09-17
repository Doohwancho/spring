package io.charIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class BufferedReaderTest {
	public static void main(String[] args) {
		BufferedReader br = null; //char 단위 reader
		PrintWriter pw = null;
		
		try {
			br = new BufferedReader(new FileReader("src/io/BufferedReaderTest.java")); //decorator 
			pw = new PrintWriter(new FileWriter("test.txt"));
			
			String line = null;
			
			while((line = br.readLine()) != null) {
				pw.println(line);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
			try {
				br.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
