package thread.level3_java_solution1_synchronized;

public class SynchronizedBlockTest {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		MusicBox box = new MusicBox();
		
		MusicPlayer kang = new MusicPlayer(1, box);
		MusicPlayer kim = new MusicPlayer(2, box);
		MusicPlayer lee = new MusicPlayer(3, box);
		
		kang.start();
		kim.start();
		lee.start();
		
		
		try {
			kang.join(); //.join()은 쓰레드 1,2,3이 끝날 때 까지 이걸 실행시킨 메인 메서드 기다리는 것. 
			kim.join();
			lee.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("총 걸린 시간: "+(endTime - startTime)+ " mili-seconds");
	}
}

/*

---
what is this code?


Thread 1,2,3(MusicPlayer 1,2,3)이 MusicBox라는 하나의 객체를 공유하면서 사용한다.

Thread 1 -> play fun music
Thread 2 -> play exciting music
Thread 3 -> play blissful music


---
experiment1. 그냥 돌리면?


console.log 


fun music
총 걸린 시간: 2 mili-seconds
exciting music
blissful music
blissful music
exciting music
exciting music
exciting music
fun music
exciting music
blissful music
fun music
fun music
blissful music
fun music
blissful music



결과
1. 뒤죽박죽 서순
2. race condition




---
experiment2. 메서드에 synchronized 붙이면?




console.log

fun music
총 걸린 시간: 2 mili-seconds
fun music
fun music
fun music
fun music
blissful music
blissful music
blissful music
blissful music
blissful music
exciting music
exciting music
exciting music
exciting music
exciting music



결과
1. 서순 깔-끔
2. race condition 해결



왜 서순이 깔끔하지?

java synchronized는 monitor로 구현되었다고 한다.
그러니 모니터랑 원리가 똑같겠지.

쓰레드 1,2,3이 동시 출발했는데,
쓰레드 1이 제일 빨리 도착해서 lock권한을 얻고, 
playMusicA();를 실행.
뒤에 도착한 2,3은 wait()을 받아서 waiting장소로 보내짐.
쓰레드 1이 다 끝나고 lock 반환 후 exit
모니터가 notifyAll();해서 잠자던 쓰레드 2,3을 다시 출발선에 보냄. 
모니터가 .acquire(); 함.
이번엔 쓰레드 3이 더 빨랐음. 쓰레드3이 lock권한 얻고 playMusicC() 실행.
쓰레드2는 wait()실행 후 다시 waiting 장소로 보내짐.
이전 단계 반복.
 




---
experiment3. 꼭 필요한 부분에만 synchronized를 붙이면?




Q. 왜 메서드 전체에 안붙이고 딱 고부분만 붙이는거야?

A. 메서드에 synchronized 붙이면,   
쓰레드 A가 메서드 A에 for loop다 돌 떄까지, 
쓰레드 B,C는 손가락 빨면서 기다려야하잖아.

만약 잘 못짜여진 코드라 저 블럭 안에 불필요한 다른 연산 있다면,  다른 쓰레드들 기다려야 하니깐. 





console.log



fun music
총 걸린 시간: 2 mili-seconds
blissful music
exciting music
blissful music
exciting music
fun music
fun music
exciting music
exciting music
blissful music
exciting music
fun music
fun music
blissful music
blissful music



메서드 전체를 synchronized하지 않아서 
서순은 다시 뒤죽박죽이 되었지만,

thread-safe하고, 
메서드 코드가 조잡하다는 가정 하에, 더 optimized 되었다.




*/