package functional.functional_interface.predicate;

public class Main {
    public static void main(String[] args) {
    	
    	//case1)
        Predicate<Integer> integerPredicate = num ->  num > 10;
        Predicate<Integer> integerPredicate1 = num -> num < 20;

        System.out.println(integerPredicate.test(5)); //false
        System.out.println(integerPredicate.and(integerPredicate1).test(15)); //true
        
        
        //case2)
        Predicate<Integer> p = i -> i < 100;
        Predicate<Integer> q = i -> i < 200;
        Predicate<Integer> r = i -> i%2 == 0;
        Predicate<Integer> notP = p.negate();

        // 100 <= i && (i < 200 || i%2==0)
        Predicate<Integer> all = notP.and(q.or(r));
        System.out.println(all.test(150));  // true
    }
}

/*

---
what is predicate?


from dictionary:

in grammar, the part of a sentence that contains the verb and gives information about the subject:

ex. In the sentence "We went to the airport", "went to the airport" is the predicate.



검증할 때 쓰는앤가보네?


*/