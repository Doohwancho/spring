package _4_io.betweenThreads;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class OutputThread extends Thread{
	PipedWriter output = new PipedWriter();
	
	OutputThread(String name){
		super(name); 
	}
	
	public void run() {
		try {
			String msg = "Hello";
			System.out.println(getName() + "sent : " + msg);
			output.write(msg);
		} catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public PipedWriter getOutput() {
		return output;
	}
	
	public void connect(PipedReader input) {
		try {
			output.connect(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
