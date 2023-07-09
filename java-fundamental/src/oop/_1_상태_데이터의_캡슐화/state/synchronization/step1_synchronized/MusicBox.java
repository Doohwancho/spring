package oop._1_상태_데이터의_캡슐화.state.synchronization.step1_synchronized;

public class MusicBox {

	public void playMusicA() { //synchronized, experiment2
		for(int i = 0; i < 5; i++) {
			synchronized (this) { //experiment3
				System.out.println("fun music");
			}
			
			try {
				Thread.sleep((int)Math.random() * 2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void playMusicB() { //synchronized, experiment2
		for(int i = 0; i < 5; i++) {
			synchronized (this) { //experiment3
				System.out.println("exciting music");
			}
			
			try {
				Thread.sleep((int)Math.random() * 2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void playMusicC() { //synchronized, experiment2
		for(int i = 0; i < 5; i++) {
			synchronized (this) { //experiment3
				System.out.println("blissful music");
			}
			
			try {
				Thread.sleep((int)Math.random() * 2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
