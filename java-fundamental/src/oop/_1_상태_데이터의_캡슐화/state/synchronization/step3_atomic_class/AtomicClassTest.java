package oop._1_상태_데이터의_캡슐화.state.synchronization.step3_atomic_class;

public class AtomicClassTest {
	
	/*
	 * example1. single thread 
	 * 
	 * locked이 false일때 동안, return true.
	 * 
	 * single thread면 상관 없는데, 멀티 쓰레드 환경에서 여러 쓰레드가 동시에 lock()실행한다면, 
	 * 한 쓰레드만 return true고 나머지는 return false 나와야 하는데, 죄다 return true 할 위험이 있다.
	 * 동시에 참조하기 때
	 * 
	 */
	
	public static class Example1SingleThread {
	    private boolean locked = false;
	 
	    public boolean lock() {
	        if(!locked) {
	            locked = true;
	            return true;
	        }
	        return false;
	    }
	}
	/*
	 * example2. compare and swap algorithm
	 * 
	 * 
	 * 
	 * atomic class는 compare and swap 알고리즘을 통해 동기화 문제를 해결한다.
	 * 
	 * compare and swap 방식이란, .setter(newVal);로 때려박는게 아니라,
	 * 
	 * .setter(oldVal, newVal); 해서, oldVal이 int i랑 똑같은 때에만 .setter(newVal); 해주는 것이다.
	 * 
	 * 
	 * 근데 이거랑 동기화랑 뭔상관?
	 * 
	 * thread A,B,C가 int i = 0을 동시에 A=1, B=2, C=3로 바꾸려고 한다.
	 * 
	 * 원래 같았으면, A.setI(1); B.setI(2); C.setI(3); 동시에 일어나서 맨 마지막에 값이 1인지 2인지 3인지 모름.
	 * 
	 * 근데 기존 값과 같은 애만 바꿀 수 있게 해주면,
	 * 
	 * A.CAS(0, 1); B.CAS(0,2); C.CAS(0,3); (where .CAS(prevVal, updateVal); 해서,
	 * 만약에 B가 먼저 도착해서 2로 바꿔버렸다면,
	 * 
	 * i = 2이니까, A.CAS(0,1); C.CAS(0,3);은 동작 못함. 0!=2 여서 막히니까.
	 * 이렇게 멀티 쓰레드 환경에서 동시성을 보장함 
	 * 
	 */

	public class Example2CompareAndSwap {
	    int val;
	    
	    public synchronized boolean compareAndSwap(int oldVal, int newVal) {
	        if(val == oldVal) {
	            val = newVal;
	            return true;
	        } else {
	            return false;
	        }
	    }
	}
	
	/*
	 * example3. AtomicInteger 뜯어보기 
	 * 
	 * 
	 * AtomicInteger 내부에 CAS algorithm이 구현되어있다. compareAndSet()이 성공할 때 까지, while을 통해 무한 루프를 돈다. 
	 * compareAndSet() 안에는 compareAndSwapInt()라는 메서드를 호출하는데,
	 * 이는 RAM메모리에 Process 공유자원에 저장된 값과 thread cache에 로컬로 저장된 값을 비교하는 함수다.  
	 * 
	
	public class AtomicInteger extends Number implements java.io.Serializable {
		
	    private volatile int value; //volatile 가 붙은 키워드는 CPU캐시가 아닌 메인 메모리에서 값을 참조해온다. 
	    //Q. volatile 붙일꺼면, 왜 CAS 알고리즘 씀?
	   	
	   	//A. volatile은 멀티 쓰레드 환경에서 한 쓰레드만 쓰고 나머지 쓰레드는 read only할 때에만 유효한 방법. 멀티 쓰레드에서 여러 쓰레드가 읽고/쓰기 병행하니까 CAS알고리즘이라는 2중장치 도입한  

	    public final int incrementAndGet() {
	        int current;
	        int next;
	        do {
	            current = get();
	            next = current + 1;
		} while (!compareAndSet(current, next)); 
			return next;
	    }
		
	    public final boolean compareAndSet(int expect, int update) {
	        return unsafe.compareAndSwapInt(this, valueOffset, expect, update); //RAM에 process 공유자원의 값과 local thread cache에 expect과 비교해서 같으면 update 값을 넣어주는 함수 
	    }	
	}

	 */
}
