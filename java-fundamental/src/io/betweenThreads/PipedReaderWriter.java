package io.betweenThreads;

public class PipedReaderWriter {
	public static void main(String[] args) {
		
		InputThread inThread = new InputThread("InputThread");
		OutputThread outThread = new OutputThread("OutputThread");
		
		//InputReader와 PipedWriter를 연결한다. 
		inThread.connect(outThread.getOutput()); //먼저 두 쓰레드 연결한 후, 시작한다. 
		
		inThread.start();
		outThread.start();
	}
}

/*

두 thread 사이에 i/o 주고받을 때. 

*/