package memory.callByValue;

public class Main {
    public static void main(String[] args) {
        User user = new User(10);

        increaseAge(user);

        System.out.println(user.getAge());
    }

    public static void increaseAge(User user){ // 원본 user의 주솟값을 그대로 넘기는게 아니라, '복사해서' User user에 넣음. (call by value, not reference)

        //case1)
//        user.setAge(user.getAge() + 1); // 11

        //case2)
        user = new User(756); // 10 왜???
    }
}


//Q. 왜 case2)에서 user의 값이 756이 아닌 10으로 출력되는가?

//A. case1)에서는 user의 값을 변경했지만, case2)에서는 user의 값을 변경하지 않았다.

//뭔 일이 일어난거지?

/******************************/
//A. call by value

//값에 의한 호출

//함수가 호출될 때, 메모리 공간 안에서는 함수를 위한 별도의 임시공간이 생성됨 (종료 시 해당 공간 사라짐)
//call by value 호출 방식은 함수 호출 시 전달되는 변수 값을 복사해서 함수 인자로 전달함
//이때 복사된 인자는 함수 안에서 지역적으로 사용되기 때문에 local value 속성을 가짐
//**따라서, 함수 안에서 인자 값이 변경되더라도, 외부 변수 값은 변경안됨**


//example in c

/*

void func(int n) { //복사해서 전달. local variable 처럼 동작
    n = 20; //복사한 local 변수 값 수정
}

void main() {
    int n = 10;
    func(n);
    printf("%d", n); //10
}

 */



//B. call by reference

//참조에 의한 호출

//call by reference 호출 방식은 함수 호출 시 인자로 전달되는 변수의 레퍼런스를 전달함
//따라서 함수 안에서 인자 값이 변경되면, 아규먼트로 전달된 객체의 값도 변경됨

/*

example in c

void func(int *n) { //원본의 주솟값 전달
    *n = 20; //원본의 값 수정
}

void main() {
    int n = 10;
    func(&n);
    printf("%d", n); //20
}

 */



//C. what happened? (call by value)

//1. User user = new User(10); 시, data runtime area에서 method area에 있는 User 클래스 정보를 사용해서 heap에 공간 받고, 0으로 초기화 하고, 생성자랑 메서드 호출할 주솟값 넣고, user에 heap의 주소를 할당해줌. (user의 주솟값은 0x12, user가 가르키는 주솟값은 0x34라고 가정)
//2. increaseAge(User user)시, 로컬 변수 user에(주솟값 0x56) 원본 user가 가르키는 값 0x34을 복사해서 넣어줌. (call by value, not reference)
//  중간정리 하면, 원본 user(0x12 -> 0x34) 이고, 로컬 user(0x56 -> 0x34)임
//3. 이 상태에서 increaseAge()안에서 user = new User(756);을 함.
//  그러면 malloc으로 heap에서 공간 받아온 다음(0x78 이라 가정),
//  0으로 초기화하고, 생성자랑 메서드 호출할 주솟값 넣고, user(0x56)에 heap의 주소(0x78)을 할당해줌.
//  그럼 로컬 user(0x56 -> 0x34)가 가르키던 값이 (0x56 -> 0x78)로 바뀜. (원본은 여전히 user는 0x12 -> 0x34)
//4. 다시 메인으로 돌아가서, user.getAge(); 을 하면, user(0x12 -> 0x34)가 가르키는 값이 756이 아니라 10임. (원본은 여전히 user는 0x12 -> 0x34)





