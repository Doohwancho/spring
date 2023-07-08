package jdk_versions.jdk8.optional;

import java.util.Optional;

public class Member {
  private Long id;
  private String name;

  private int salary;



  //step5) Optional을 필드로 사용 금지
  // Optional은 필드에 사용할 목적으로 만들어지지 않았으며, Serializable을 구현하지 않았다. 따라서 Optional은 필드로 사용하지 말자.
//  private Optional<String> email = Optional.empty(); //안좋음
  private String email;


  //create increase salary method
  public void increaseSalary(){
    this.salary = this.salary+1;
  }
}
