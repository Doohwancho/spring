package thread.level10_critical_section_monitor;

import com.google.common.util.concurrent.Monitor;

public class MonitorTest extends SequenceGenerator{
    private Monitor mutex = new Monitor();

    @Override
    public int getNextSequence() {
        mutex.enter();
        try {
            return super.getNextSequence();
        } finally {
            mutex.leave();
        }
    }

}

/*

---
what is monitor?


세마포어에서 화장실 들어갔다가 나왔다 할 때,
lock 권한 주고받는걸 .wait() .notify()로 하는데,
둘 중 하나가 작동 안했거나,
wait() 이후 notify() 타이밍 안맞아서 서순 안맞으면, 데드락걸림.

이걸 방지하기 위해 나온게 monitor이라고 함. 

monitor은 wait(), notify() 관리해주는 솔루션. 


자바의 synchronized도 모니터를 통해 생성되었다. 


---
코드 뼈대

acquire(m)						//모니터락 취득(mutex lock)
while (!p){						//조건확인
	wait(m,cv)					//조건 충족 안되면 waiting (entry queue)
} 
작업 수행 
notify(cv2) or notifyAll(cv2)	//waiting하고 있는 thread들 깨움
release(m)						//모니터 락 반환 (뮤텍스 락)



---
monitor의 구조

A. Entry Set
	1. enter
		thread state becomes 'Runnable' -> 'Blocked'
	2. acquire 
		2-1. (Entry Set -> Monitor with lock)
		2-2. (Entry Set -> Wait Set), wait();
			lock is granted
			but if Monitor is already taken
			return lock
			go to Wait Set 
			thread state is 'Waiting' or 'Time Waiting'
B. Monitor (Critical Section)
	3. 임계영역에서 단일로 일처리 
	4. release and exit(Monitor -> exit)
	5. notify() || notifyAll()
		Wait Set에 있는 놈들 다 Entry Set으로 보냄
		다시 'Waiting' 상태에서 'Blocked' 상태로 됨
		monitor lock 권한 획득을 위해 여러 쓰레드가 기다림. 
C. Wait Set 
	 
			

*/
