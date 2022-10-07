package __2.객체_생성과_파괴.__7;

import java.util.Arrays;
import java.util.EmptyStackException;

//Q. 메모리 누수가 일어나고있는 곳은? 

public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        //before
//        return elements[--size];
        
        //after
        Object result = elements[--size];
        elements[size] = null; // 다 쓴 참조 해제. null을 넣어서 해제시켜주네? 
        //왜 메모리 누수됨? -> 스택은 자기가 elements라는 Object[]만들어서 메모리관리 직접하거든. 비활성 영역의 값들이 안쓰인다고 GC가 맘대로 해제도 못해서 메모리 누수가 생김. 
        return result;
    }

    /**
     * 원소를 위한 공간을 적어도 하나 이상 확보한다.
     * 배열 크기를 늘려야 할 때마다 대략 두 배씩 늘린다.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}