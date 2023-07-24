package oop._1_상태_데이터의_캡슐화.nested_class.멤버_클래스.custom_list_with_iterator;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        CustomList customList = new CustomList();
        customList.add("item1");
        customList.add("item2");
        customList.add("item3");
    
        Iterator iterator = customList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
