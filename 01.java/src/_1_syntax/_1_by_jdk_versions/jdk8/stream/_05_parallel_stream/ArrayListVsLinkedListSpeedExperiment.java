package _1_syntax._1_by_jdk_versions.jdk8.stream._05_parallel_stream;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** experiment */
//parallel stream 했을 때, ArrayList vs LinkedList 중, 뭐가 더 빠를까?
public class ArrayListVsLinkedListSpeedExperiment {
  public static void work(int value) {
  }

  public static long testParallel(List<Integer> list) {
    long start = System.nanoTime();
    list.stream().parallel().forEach(ArrayListVsLinkedListSpeedExperiment::work);
    long end = System.nanoTime();
    long runTime = end - start;
    return runTime;
  }

  public static void main(String[] args) {
    List<Integer> arrayList = new ArrayList<>();
    List<Integer> linkedList = new LinkedList<>();
    for (int i = 0; i < 1000000; i++) {
      arrayList.add(i);
      linkedList.add(i);
    }

    long arrayListParallel = testParallel(arrayList);
    long linkedListParallel = testParallel(linkedList);

    arrayListParallel = testParallel(arrayList);
    linkedListParallel = testParallel(linkedList);
    
    System.out.println("arrayListParallel: " + arrayListParallel+" nano seconds"); //비록 둘이 같게 나오지만, 더 미세한 시간 비교하면 ArrayList가 더 빠르다 
    System.out.println("linkedListParallel: " + arrayListParallel+" nano seconds");

    if (arrayListParallel < linkedListParallel) {
      System.out.println("성능 테스트 결과: ArrayList가 더 빠름"); //왜 ArrayList가 더 빠름? -> index based random access지원하기 때문. 쪼개는게 미세하게 더 빠름. 연산하고 합치는 시간은 같은데. 
    } else {
      System.out.println("성능 테스트 결과: LinkedList가 더 빠름");
    }
  }
}