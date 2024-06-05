package _1_syntax._2_syntax_details.memory.methodStackHeap;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        swap(person);
        System.out.println(person.age1); //20
        System.out.println(person.age2); //10

        Person person2 = new Person();
        System.out.println(person2.age1); //10
        System.out.println(person2.age2); //20
    }

    public static void swap(Person person){
        int temp = person.age1;
        person.age1 = person.age2;
        person.age2 = temp;
    }
}



//Q. 이 코드가 실행되면, jvm runtime data area에 무슨일이 벌어질까?

//A. main method's stack frame
//1. main() 메소드가 호출되면, main() 메소드를 위한 stack frame이 생성됨
//2. stack.local_variables_array에 args, person에 메모리 공간 할당 후, Person person = new Person(); 실행
//3. Person person = new Person();은 제일 먼저 heap에 메모리 공간 마련하고 Person 인스턴스 적재함.
//4. heap.Person에 접근할 수 있는 참조값(주솟값)을 stack.operand_stack에 push함.
//5. stack.operand_stack에 push된 참조값을 pop해서 복사하고, 다시 operand_stack에 두개 다 push 함.(동일 Person 주솟값 2개 존재)
//6. stack.operand_stack.pop() -> Person 인스턴스 실행

//B. Person's stack frame
//7. Stack에 Person's stack frame을 적재(main stack frame 위)
//8. Person에 선언한 인스턴스 변수는 local 변수와는 달라서, stack.local_variables에 저장되는게 아니라, heap에 Person과 함께 저장됨
//9. Person의 생성자 실행. stack.local_variables_array에 0번 인덱스 읽어 this를 stack.operand_stack에 push함.
//10. this란 자기자신, Person 인스턴스의 주솟값을 가리키는 참조값임.
//11. super class인 object class를 호출하기 위해, stack.operand_stack을 pop하여 this 참조값 전달. (이 때, 생성자 만들어지고 새 stackframe 추가되어야 하나 생략)
//12. object class의 실행흐름이 끝나면, 다시 Person 실행흐름으로 넘어옴
//13. 다시 stack.local_variables_array에 0번 인덱스 읽어 this를 stack.operand_stack에 push 한 후, Person 인스턴스의 age1에 10을 stack.operand_stack에 push함.
//14. stack.operand_stack에 push된 this, age1 을 두번 pop하여 stack.local_variables_array에 this에 age1에 상수값 10을 저장함 (age1 = 10)
//15. 이 과정을 age2에 대해서도 반복함 (age2 = 20)
//      thought) 아 이 과정이 클래스에 인스턴스 변수가 있는데, new Person()해서 새로운 인스턴스 만들 때, 고 인스턴스 안 변수에 값으로 받아오는 과정이구나
//16. 인스턴스의 참조값 stack.local_variables_array에 this를 반환한다는 말인 듯
//      thought) 그래서 Person person = new Person(); 하고, print(person); 하면, person의 주솟값이 출력되는데, this도 person의 주솟값이니까.

//C. main's stack frame
//17. main() 메소드의 실행흐름으로 돌아옴
//18. person 인스턴스의 참조값(주솟값)이 stack.operand_stack에 push됨
//19. stack.operand_stack에 push된 참조값을 pop하여 stack.local_variables_array에 저장함. (이 때, [0]엔 args가 있고, [1]엔 person의 주솟값이 있음
//20. swap()메서드 호출하기 전, stack.local_variable_array에 저장된 person의 주솟값을 stack.operand_stack에 push함
//21. stack.operand_stack에 push된 참조값을 pop하여 swap()메서드의 인자로 전달함


//D. swap's stack frame
//22. Stack에 swap's stack frame을 적재(main stack frame 위)
//23. stack.local_variables_array에 0번 인덱스엔 person의 주솟값이 있고, 1번 인덱스엔 temp의 사이즈 만큼 메모리에 적재
//24. stack.operand_stack에 stack.local_variables_array에 0번 인덱스에 저장된 person의 주솟값을 push함
//25. stack.operand_stack을 pop하여 person의 참조값을 통해 age1의 인스턴스 값인 10을 읽어 stack.operand_stack에 push함
//26. operand_stack을 pop하여 local_variables_array에 1번째 인덱스에 temp에 10을 저장함. 여기까지가 int temp = person.age1;
//27. person.age1 = person.age2; 은 heap에 person.age2 를 person.age1에 덮어씌움. person 2번 참조해야하니까, local_variable_array에 [0]에 person 주솟값을 operand_stack에 2번 push 후, pop해 값 읽어서 덮어쓰기
//28. person.age2 = temp; 은 local_variable_array의 temp에 저장된 10을, local_variable_array[0] -> operand_stack에 push, pop 후, person.age2에 덮어씌움
//29. swap() 메서드의 실행흐름이 끝나면, return void -> stack.frame을 제거함 -> 실행흐름이 main으로 돌아옴

//E. main's stack frame
//30. main() 메서드의 실행흐름으로 돌아옴
//31. return void -> stack frame이 모두 clear됨



//Q. java는 call by value 라면서? 원본 안바뀐다며?
//A. method area에 Person정보가 바뀐게 아니라, new할 때 heap에 만들어진 Person 인스턴스가 바뀐거잖어.
//   참조값으로 heap에 변수 바꾼거면, stack frame 안에서만 있는 로컬 변수 바꾼게 아님.

//reference: https://www.youtube.com/watch?v=Vd1C3-wHc4Y&ab_channel=%EC%BD%94%EB%93%9C%EB%9D%BC%EB%96%BC