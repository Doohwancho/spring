package com.cos.person.aop.step3monitorTime;

//@SpringBootApplication
public class AopDemoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(AopDemoApplication.class, args);
    SortUtil aopTest = context.getBean(SortUtil.class);

    final List<Integer> list = new ArrayList<>(Arrays.asList(99, 7, 78, 10, 17, 8, 19, 91, 15, 1));

    aopTest.bubbleSort(list.stream().mapToInt(Integer::intValue).toArray());
    aopTest.insertionSort(list.stream().mapToInt(Integer::intValue).toArray());
    aopTest.selectionSort(list.stream().mapToInt(Integer::intValue).toArray());
    aopTest.mergeSort(list.stream().mapToInt(Integer::intValue).toArray());
    aopTest.quickSort(list.stream().mapToInt(Integer::intValue).toArray());
    System.exit(0);
  }
}