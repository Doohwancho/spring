---\
what is TDD?

test driven development
1. write a failing test
2. write code that make it pass
3. refactor | goto 1


---\
why not TAD(test after development)?

1. 성가심 (이미 개발 끝났는데 추가 일하는 느낌 -> shortcut)
2. 테스트 신뢰 불가능. (shortcut 했으니, 어딘가 구멍이 있겠지? 의심감 -> 불안)
3. 지루함 (문제를 보고 고민하여 답을 찾는 쾌감이 아닌, 답에 억지로 문제 끼워맞춤)


---\
why TDD?

1. fun (deconstruct & reconstruct)
2. preventive (better to pre-design than problem->debugging)
3. TDD well -> auto-documentation
4. decoupling (independent production code)
5. no longer afraid of changing spaghetti code


---\
tips

A. minimal, inductive approach\
B. abstract\
C. decoupling 


---\
details on tips

A. minimal, inductive approach

A.1. unit test at a time, from simplest to complex\
A.2. ideally, one assertion per one test method\
A.3. do not print anything in unit test


B. abstract\
그 함수의 핵심 로직을 가장 일반화된(추상화된) 형태로 테스트 한다.


C. Decoupling\
불확실성 제거\
(ex. 개발자가 제어할 수 없는 값에 의존하는 코드)

why?\
테스트는 실행할 때마다 같은 결과를 만들어야 한다.

<br/>
C.1. 외부 라이브러리에 의존

- `Random()`, `new Date()` (`LocalDate.now()`) 와 같이 실행할때마다 결과가 다른 함수에 의존하는 경우
- `readLine` 혹은 `inputBox` 등 사용자들의 입력에 의존하는 경우
- **전역 함수**, **전역 변수** 등에 의존하는 경우
- PG사 라이브러리등 외부 SDK에 의존하는 경우
- `console.log`, `System.out.println()` 과 같은 표준 출력
- Logger 등을 사용하는 경우
- 이메일 발송, 메세지큐 등 외부로의 메세지 발송
- 데이터베이스 등에 의존하는 경우
- 외부 API에 의존하는 경우

new Date() 테스트시, 월요일이 나오면, 월요일에만 테스트 통과함 ㅋㅋ


<br/>
<br/>
C.2. 개별 테스트는 transaction

테스트 메서드 끼리도 의존하면 안된다.


ex.
트랜젝션 종료 후 데이터 초기화 되는데,\
primary key auto_increment는 값이 초기화가 안됨\
그래서 에러나는 경우가 있다.

<br/>
<br/>
C.3. 계층간 역할 분리 on MVC

controller, service, repository\
각 레이어단 마다, 맡은 역할이 다르기 때문에,\
그 역할에 맞게 테스트 해줘야 한다.

예를들어, Book Entity의 author가 null인지 체크는 repository의 책임이 아니다.

애초에 데이터를 이상하게 쏘는지 필터는 완전 프런트 단의 js단에서 하거나,\
백엔드로 넘어왔다면, 적어도 controller에서 걸러줘야 한다.

각 계층해서 해야할 책임을 다른 계층으로 떠밀면 안된다.


<br/>
<br/>
D. etc

D.1. 테스트 메서드는 순서 보장이 안된다.\
비동기 실행이기 때문.\
따라서 @Order(1) @Order(2) @Order(3) 으로 순서 명시해 줘야 한다




