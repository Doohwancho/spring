package _4_io.betweenThreads;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.StringWriter;

public class InputThread extends Thread{
	PipedReader input = new PipedReader();
	StringWriter sw = new StringWriter();
	
	InputThread(String name){
		super(name); 
	}
	
	public void run() {
		try {
			int data = 0;
			
			while((data = input.read()) != -1) {
				sw.write(data);
			}
			System.out.println(getName() + " received : " + sw.toString());
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				sw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public PipedReader getInput() {
		return input;
	}
	
	public void connect(PipedWriter output) {
		try {
			input.connect(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
