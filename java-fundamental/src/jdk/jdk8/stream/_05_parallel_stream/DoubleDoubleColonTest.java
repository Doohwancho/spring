package jdk.jdk8.stream._05_parallel_stream;

import java.util.Arrays;
import java.util.List;
import jdk.jdk8.stream.MaleStudent;
import jdk.jdk8.stream.Student;
/*
---
Q. :: ?


Map<String, Person> personMap = personList.stream()
        .collect(Collectors.toMap(Person::getName, Function.identity()));


여기에서, Person::getName 은 뭘까?
가끔 System.out::println 도 쓰던데.

Stream<String> stream
            = Stream.of("Geeks", "For",
                        "Geeks", "A",
                        "Computer",
                        "Portal");

stream.forEach(s -> System.out.println(s));
stream.forEach(System.out::println); //위에 코드와 같다.



<Class name>::<method name>
해당 클래스 안에 메서드를 쓰겠다는 것.
 */

public class DoubleDoubleColonTest {
  public static void main(String[] args) {
	    List<Student> totalList = Arrays.asList(
	        new Student("홍길동", 10, Student.Sex.MALE),
	        new Student("홍길순", 12, Student.Sex.FEMALE),
	        new Student("김남", 10, Student.Sex.MALE),
	        new Student("김여", 8, Student.Sex.FEMALE)
	    );

	    System.out.println("step1) using lambda\n");

	    MaleStudent maleStudentListLambda = totalList.parallelStream()
	        .filter(s -> s.getSex() == Student.Sex.MALE)
	        .collect(
	            () -> new MaleStudent(), //.collect(요소, 누적기, 저장될 컬렉션) 
	            (r, t) -> r.accumulate(t), //??? r,t 어디서 튀어나온겨? r은 MaleStudent. 그래서 r.accumulate()가 가능한 것. t는 Student
	            (r1, r2) -> r1.combine(r2)); //??? r1, r2는 어서 튀어나온겨?  -> r1도 MaleStudent. 그래서 r1.combine()이 가능한 것. r2도 MaleStudent.
	    maleStudentListLambda.getList().stream().forEach(s -> System.out.println(s.getName()));
	    /*
	     	---
			console.log
			

			using lambda
			[main] MaleStudent()
			[main] accumulate()
			[ForkJoinPool.commonPool-worker-1] MaleStudent() //각 쓰레드가 new MaleStudent()함
			[ForkJoinPool.commonPool-worker-3] MaleStudent()
			[ForkJoinPool.commonPool-worker-2] MaleStudent()
			[ForkJoinPool.commonPool-worker-3] accumulate()
			[ForkJoinPool.commonPool-worker-1] combine()
			[ForkJoinPool.commonPool-worker-2] combine()
			[ForkJoinPool.commonPool-worker-2] combine()
			홍길동
			김남


			머지소트처럼되네?
			main이 .accumulate()로 thread 1,2중에 new MaleStudent() 하나 먹고,
			thread3이 .accumulate()로 thread 1,2중에 new MaleStudent() 하나 먹어서 
			
			main - thread1 - thread2 - thread3 이렇게 되있으니까,
			.combine()가 총 n-1번 실행되어
			 (main - thread1) - thread2 - thread3
			 (main - thread1 - thread2) - thread3
			 (main - thread1 - thread2 - thread3)
			 
			 이렇게 묶이게 되네?
			 
			 
			 다시 생각해보니까, merge sort는 2개씩 재귀로 쪼개나가는데,
			 얘는 시작하자마자 필요한 쓰레드만큼 팍! 코어의 갯수만큼 쪼개지니까 좀 다르긴 한데,
			 여튼 분할해서 동시처리한다음 합칠 땐 머지소트랑 비슷하게 가는...
			 그것도 아닌게, 머지소트는 합칠 때 마다 반씩 줄어드는데,
			 얘는 n-1번 합쳐야 하니까 좀 다르긴 하네. 
	     */

	    System.out.println("\nstep2) using method reference ::\n");

			//step1과 똑같은데, 차이점은 ::로 간소화 시켰냐의 차이.
	    MaleStudent maleStudentListMethodReference = totalList.parallelStream()
	        .filter(s -> s.getSex() == Student.Sex.MALE)
	        .collect(MaleStudent::new, MaleStudent::accumulate, MaleStudent::combine); //.collect(요소, 누적기, 저장될 컬렉션) 
	    maleStudentListMethodReference.getList().stream().forEach(s -> System.out.println(s.getName()));
	    
	    /*
			---
			console.log
			
			
			 using method reference
			 
			[main] MaleStudent()
			[main] accumulate()
			[ForkJoinPool.commonPool-worker-3] MaleStudent()
			[main] MaleStudent()
			[main] combine()
			[ForkJoinPool.commonPool-worker-1] MaleStudent()
			[ForkJoinPool.commonPool-worker-1] accumulate()
			[ForkJoinPool.commonPool-worker-1] combine()
			[ForkJoinPool.commonPool-worker-1] combine()
			홍길동
			김남

			
			얘도 멀티쓰레드라 순서가 뒤죽박죽이라 그렇지 위와 똑같은 듯 
			
	     */
	  }
}



/*

---
일반 싱글 쓰레드였다면?

1. MaleStudent 객체는 하나만 생성
2. 해당 객체에 .add()가 계속 호출되어 MaleStudent 객체 안에 계속 누적됨 
3. .combine()메서드는 전혀 호출되지 않음. 



---
병렬 쓰레드로 처리하면?

1. 코어의 개수 만큼 전체 요소는 서브 요소로 나뉘어지고, 해당 개수 만큼 스레드가 생성된다.
2. 각 스레드는 서브 요소를 수집해야하므로 4개의 MaleStudent 객체를 생성하기 위해 collect()의 첫번째 메소드 참조인 MaleStudent::new를 4번 실행시킨다.
3. 각 스레드는 MaleStudent 객체에 남학생 요소를 수집하기 위해 collect()의 두번째 메소드 참조인 MaleStudent::accumulate를 매번 실행시킨다.
4. 수집 완료된 MaleStudent는 (코어 개수 - 1) 번의 결합으로 최종 수집된 MaleStudent로 만들어 진다. 따라서 collect()의 세번째 메소드 참조인 MaleStudent::combine() 이 (코어 개수 -1)번 실행된다.




---
주의사항

1. 병렬처리가 상항 싱글쓰레드 처리보다 빠른게 아니다! 
	컬렉션에 요소수가 적으면, 쓰레드풀 생성, 쓰레드 생성 시간 때문에, single thread 처리가 parallel thread 처리보다 더 빠를 수 있다.
	
2. 싱글 코어 CPU는 병렬처리보다 순차처리가 더 빠르다. 병렬처리하면, 쓰레드 수만 증가할 뿐, 번갈아가면서 스케쥴링해야해서 오히려 비효율. 코어 수가 많은 컴퓨터에서 병렬처리 하자.
3. ArrayList의 경우 index로 접근하기 때문에 merge sort에 초반에 자르는 것 처럼, 분리가 빠른데, HashSet, TreeSet 이런 애들은 분리하기 쉽지 않고, index 기반 랜덤 엑세스 지원 안되니까 더 느리다. 

*/
