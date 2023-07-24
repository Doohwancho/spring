package oop._1_상태_데이터의_캡슐화.nested_class.멤버_클래스.custom_list_with_iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomList {
    private Object[] elements = new Object[10];
    private int size = 0;
    
    public void add(Object element) {
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        elements[size++] = element;
    }
    
    public Iterator iterator() {
        return new CustomListIterator();
    }
    
    private class CustomListIterator implements Iterator {
        private int currentIndex = 0;
        
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }
        
        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[currentIndex++];
        }
    }
}
