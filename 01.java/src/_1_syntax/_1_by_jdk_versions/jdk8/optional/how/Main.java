package _1_syntax._1_by_jdk_versions.jdk8.optional.how;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import _1_syntax._1_by_jdk_versions.jdk8.optional.Member;

/*
    Summary

    1. `isPresent()-get()` 대신 `orElse()/orElseGet()/orElseThrow()`
    2. `orElse(new ...)` 대신 `orElseGet(() -> new ...)`
    3. 단지 값을 얻을 목적이라면 `Optional` 대신 `null` 비교
    4. `Optional` 대신 비어있는 컬렉션 반환
    5. `Optional`을 필드로 사용 금지
    6. `Optional`을 생성자나 메서드 인자로 사용 금지
    7. `Optional`을 컬렉션의 원소로 사용 금지
    8. `of()`, `ofNullable()` 혼동 주의
    9. `Optional<T>` 대신 `OptionalInt`, `OptionalLong`, `OptionalDouble`
 */

public class Main {

  public Member getMember(){
    return null;
  }

  public List<Member> getTeam(){
    return null;
  }

  //step1) isPresent()-get() 대신 orElse()/orElseGet()/orElseThrow()
  public Member step1(){
    // 안 좋음
    Optional<Member> member = Optional.ofNullable(getMember());
    if (member.isPresent()) {
      return member.get();
    } else {
      return null;
    }

    // 좋음
//    return member.orElse(null); //위와 같은 코드, 그러나 더 간결함.
  }

  //step2) orElse(new ...) 대신 orElseGet(() -> new ...)
  public Member step2(){
    //orElse(...)에서 ...는 Optional에 값이 있든 없든 무조건 실행된다.
    //왜?
    //functionA(functionB()) 에서 functionB()가 먼저 실행되는 것과 같은 이치.

    //안 좋음
    Optional<Member> member = Optional.ofNullable(getMember());
    return member.orElse(new Member()); //member가 null이든 아니든, 무조건 new Member()을 반환한다.

    //좋음
//    return member.orElseGet(Member::new);
  }

  //step3) 단지 값을 얻을 목적이라면 Optional 대신 null 비교
  public Member step3(){
    Member status = getMember();
    Member READY = getMember();

    //Optional은 비싸다. 따라서 단순히 값 또는 null을 얻을 목적이라면 Optional 대신 null 비교를 쓰자.

    // 안 좋음
    return Optional.ofNullable(status).orElse(READY);

    // 좋음
//    return status != null ? status : READY;
  }

  //step4) Optional 대신 비어있는 컬렉션 반환
  public List<Member> step4(){
    //Optional은 비싸다. 그리고 컬렉션은 null이 아니라 비어있는 컬렉션을 반환하는 것이 좋을 때가 많다.
    //따라서 컬렉션은 Optional로 감싸서 반환하지 말고 비어있는 컬렉션을 반환하자.
    //특히 jpa repository에서 Optional 반환하지 마라.

    // 안 좋음
//    List<Member> members = team.getMembers();
//    return Optional.ofNullable(members);

// 좋음
    List<Member> members = getTeam();
    return members != null ? members : Collections.emptyList();
  }


  //step5) Optional을 필드로 사용 금지
  public void step5(){
    // Optional은 필드에 사용할 목적으로 만들어지지 않았으며, Serializable을 구현하지 않았다. 따라서 Optional은 필드로 사용하지 말자.

    //Member.java의 email 필드 봐봐

  }

  //step6) Optional을 생성자나 메서드 인자로 사용 금지
  //안 좋음
  public void step6Bad(Optional<Member> member){
    Optional<Member> member2 = Optional.ofNullable(getMember());
    member2.ifPresent(m -> m.increaseSalary());

    //Q. 왜 안좋음?
    //A. 아래처럼 호출하니까. 안티패턴임.
    //hrManager.increaseSalary(Optional.ofNullable(member));
  }

 public void step6Good(Member member){
    if(member != null){
      member.increaseSalary();
    }
    //Q. 왜 좋음?
   //A. step6Bad의 호출방법이랑 비교해봐. 깔끔하잖아.
   //hrManager.increaseSalary(member);
  }

  //step7) Optional을 컬렉션의 원소로 사용 금지
  public void step7(){
    //왜?
    //넣기 전에 null check하고 넣는게, 나중에 꺼낼 때 쉽게 뽑아 쓸 수 있잖아~
    //대충 때려박고, 나중에 꺼낼 때마다 null check하는거면, 넣는건 한번인데, 꺼내쓰는건 여러번이니까, 비효율적이지.

    // 안 좋음
    Map<String, Optional<String>> sports = new HashMap<>();
    sports.put("100", Optional.of("BasketBall"));
    sports.put("101", Optional.ofNullable("someOtherSports"));
    String basketBall = sports.get("100").orElse("BasketBall");
    String unknown = sports.get("101").orElse("");

    // 좋음
//    Map<String, String> sports = new HashMap<>();
//    sports.put("100", "BasketBall");
//    sports.put("101", null);
//    String basketBall = sports.getOrDefault("100", "BasketBall");
//    String unknown = sports.computeIfAbsent("101", k -> "");

  }

  //step8) of(), ofNullable() 혼동 주의
  public Optional<Member> step8(){
    //of(X)은 X가 null이 아님이 확실할 때만 사용해야 하며, X가 null이면 NullPointerException 이 발생한다.
    //ofNullable(X)은 X가 null일 수도 있을 때만 사용해야 하며, X가 null이 아님이 확실하면 of(X)를 사용해야 한다.

    // 안 좋음
    return Optional.of(getMember());  // member의 email이 null이면 NPE 발생

    // 좋음
//    return Optional.ofNullable(member.getEmail());
  }


  //step9) Optional<T> 대신 OptionalInt, OptionalLong, OptionalDouble
  public void step9(){
    // 안 좋음
    Optional<Integer> count = Optional.of(38);  // boxing 발생
    for (int i = 0 ; i < count.get() ; i++) {
      //...
    }  // unboxing 발생

    // 좋음
//    OptionalInt count2 = OptionalInt.of(38);  // boxing 발생 안 함
//    for (int i = 0 ; i < count2.getAsInt() ; i++) {
//      //...
//    }  // unboxing 발생 안 함
  }

}
