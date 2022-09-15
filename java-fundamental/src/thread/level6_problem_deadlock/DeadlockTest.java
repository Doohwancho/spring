package thread.level6_problem_deadlock;

public class DeadlockTest {

    public static Object object1 = new Object();
    public static Object object2 = new Object();

    public static void main(String[] args) {
        FirstThread thread1 = new FirstThread();
        SecondThread thread2 = new SecondThread();

        thread1.start();
        thread2.start();

    }

    private static class FirstThread extends Thread{
        @Override
        public void run() {
            synchronized (object1){
                System.out.println("First Thread has object1's lock");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("First Thread want to have object2's lock. waiting...");

                synchronized (object2){
                    System.out.println("First Thread has object2's lock too");
                }
            }
        }
    }

    private static class SecondThread extends Thread{
        @Override
        public void run() {
            synchronized (object2){
                System.out.println("Second Thread has object2's lock");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Second Thread want to have object1's lock, waiting...");

                synchronized (object1){
                    System.out.println("Second Thread has object1's lock too");
                }
            }
        }
    }
}



/*

---
what is deadlock?



멀티 쓰레드 환경에서,
쓰레드A가 object A에 대해 lock() 걸었고,
쓰레드B가 object B에 대해 lock() 걸었는데,

이젠 서로 
쓰레드A가 object B에 대해 lock() 걸고 싶어하고,
쓰레드B가 object A에 대해 lock() 걸고 싶어하네?

쓰레드 A,B가 서로의 lock()이 해제되기를 하염없이 평생 기다리는 상황
그것이 데드락.




---
console.log



First Thread has object1's lock
Second Thread has object2's lock
First Thread want to have object2's lock. so wait
Second Thread want to have object1's lock, so wait


쓰레드 1,2 모두 아무 실행 못한채 무한정 대기중.



---
deadlock 발생조건 4가지



1. 상호 배제 (Mutual Exclusion) : 한 자원에 대해 여러 쓰레드 동시 접근 불가
2. 점유와 대기 (Hold and Wait) : 자원을 가지고 있는 상태에서 다른 쓰레드가 사용하고 있는 자원 반납을 기다리는 것
3. 비선점 (Non Preemptive) : 다른 쓰레드의 자원을 실행 중간에 강제로 가져올 수 없음
4. 환형대기 (Circle Wait) : 각 쓰레드가 순환적으로 다음 쓰레드가 요구하는 자원을 가지고 있는 것


이 4가지 조건을 모두 만족해야 데드락이 걸린다.
즉, 하나만 풀어줘도 데드락이 풀린다.




---
deadlock 해결방법 4가지


1. 상호 배제 : object1과 object2 객체에 대해서 동시에 쓰레드가 사용할 수 없도록 하였습니다.
2. 점유와 대기 : FirstThread에서는 object1의 락을 가지고 있으면서 object2에 대한 락을 원하고, SecondThread는 object2에 대한 락을 가지고 있으면서 object1의 락을 획득하기를 원합니다.
3. 비선점 : 쓰레드의 우선순위의 기본값은 NORM_PRIORITY로 동일하게 설정되어 있습니다.
4. 환형대기 : FirstThread는 SecondThread의 object2 객체의 락을 대기하고 SecondThread는 FirstThread의 object1 객체의 락을 대기하고 있습니다.



4개중 하나만 풀어도 문제 해결된다. 


내 생각엔,
1. Mutual Exclusion의 개념적인건 Critical Section's Mutex이고, 구현한게 synchronized고,
2. Hold and Wait는 기존에 race condition문제 그대로 인것 같고,(공유자원 쓰면 바로 제자리 돌려놔야하는데, 다른짓 하고 그걸 다른 쓰레드 들이 기다리니까 성능저하)
	Thread.wait()로 쓰레드가 lock가지고 있으면 반환시키고, 
	Thread.notify()로 대기상태인 쓰레드에게 다시 lock의 권한을 부여하고 수행하게 만들 수 있네?  
3. Non Preemptive은 개념적인건 critical section's mutex이고 구현한건 synchronized나 Atomic Class인 것 같고,
4. Circle Wait는 서순을 바꿔줘라. 




---
solution4. 환형대기 조건 만족하기 

"락은 얻어온 순서대로 풀어야된다..."



원래 
Thread A, object1 -> ThreadB's object2
Thread B, object2 -> ThreadA's object1

이랬는데,

Thread A, object1 -> object2
Thread B, object1 -> object2

순서를 바꾼다면?


First Thread has object1's lock
First Thread want to have object2's lock. waiting...
First Thread has object2's lock too
Second Thread has object1's lock
Second Thread want to have object2's lock, waiting...
Second Thread has object2's lock too



풀렸다!

object1에 대한 접근이 synchronized로 critical section이 보장되었기 때문에, 
First Thread가 다 끝나길 기다린 다음 순서대로 Second Thread가 들어가서 무사히 잘 끝났다.









*/