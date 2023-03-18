package main.java.com.example.os.solutions.step2_mutex;

//import java.util.concurrent.locks.ReentrantLock;

public class MutexTest { //extends SequenceGenerator
//    private ReentrantLock mutex = new ReentrantLock();
//
//    @Override
//    public int getNextSequence() {
//        try {
//            mutex.lock();
//            return super.getNextSequence();
//        } finally {
//            mutex.unlock();
//        }
//    }
}



/*

---
what is mutex?


critical section이랑 같은 개념이라 이해하면 편함. 

1. 화장실이 하나밖에 없다. 기다리는 놈은 많은데. 
2. 들어간 놈은 lock이라는 권한을 얻는다. (이 권한 있으면 다른놈 못들어옴*) 
3. 나갈 때 lock 권한 반환해야 한다. 




---
mutex 구현의 한계


위에 코드는 java 1.5에 있었던 코드.
지금은 Deprecated. 

현재 자바에 순수 Mutex는 없으며, semaphore로 간접구현은 가능하다고 함.


왜 막았을까?

critical section이랑 mutex랑 본질적으론 비슷한 개념인데,
적용 범위에서 차이가 있다고 한다. 
critical section = 하나의 프로세스 안 멀티 쓰레드 의 동기화를 보장하는 반면,
mutex는 멀티 프로세스의 쓰레드 사이의 동기화를 보장해 준다고 함.


IPC... 음...
듣기만 해도 위험해 보인다.. 
잘못 만졌다가.. 







*/