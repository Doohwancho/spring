---\
what is Mock?

가짜 객체.

왜?\
실제 환경을 100% 만들어서 테스트 하면 자원 많이 들고, 의존성이 높아지니까,\
개별 테스트를 나눠서 의존성을 낮추는데, 
이 때, 원래 객체의 100%를 쓰지 않고, 그 객체의 필요 부분만 구현해서 씀.


example(of Stub)

1. saveUserIdPassword() 테스트 하고 싶다
2. repository(user.id, user.password); 라는 코드로 테스트 하고 싶다.
3. 근데 옆팀에서 패스워드 인코더 개발한단다.
4. repository(user.id, pwEncoder(user.password)); 하기엔, pwEncoder 구현이 안되있다. 의존적이다. 테스트 하기 애매하다.
5. 이 때, pwEncoder();를 임의로 return "encoded-password"; 해버리는 것이다. 
6. 이로 인해 saveUserIdPassword()의 테스트와 pwEncoder()의 의존성이 분리되면서, 유닛 테스트가 가능해졌다.

<br/>

---\
state based test vs behavior based test

- state based test
  - 특정 시점에 어떤 인풋을 넣었더니 어떤 아웃풋이 나오더라, 상태 확인 테스트
- behavior based test
  - return값이 void거나, 메서드간 실행 순서, 관계에 대한 테스트 

<br/>

---\
types of Mock

- Test Double(스턴트 맨)
  - Dummy Object
    - 껍데기 instance. return void
    - 객체만 필요하지 기능은 안필요할 때 사용 
  - Stub
    - instance + return desired output with no logic(하드코)
    - 특정 상태를 대표함 
  - Fake Object
      - Test Stub의 복수형. 여러 객체를 대표
  - Test Spy
      - Test Double 객체 중에서 Test Spy가 호출되면, 기록하도록 구현됨
  - Mock
    - Stub는 상태 검증 용 이라면, Mock은 행위 검증용


