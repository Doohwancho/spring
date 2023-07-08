package jdk_versions.jdk8.optional.how.refactoring;

import java.util.Optional;
import jdk_versions.jdk8.optional.Address;
import jdk_versions.jdk8.optional.UserVO;

public class Main {

  //step1)
  //before
  public String findPostCode1() {
    UserVO userVO = new UserVO();
    if (userVO != null) {
      Address address = userVO.getAddress();
      if (address != null) {
        String postCode = address.getPostCode();
        if (postCode != null) {
          return postCode;
        }
      }
    }
    return "우편번호 없음";
  }

  //step2)
  //after1
  public String findPostCode2() {
    // 위의 코드를 Optional로 펼쳐놓으면 아래와 같다.
    Optional<UserVO> userVO = Optional.ofNullable(new UserVO());
    Optional<Address> address = userVO.map(UserVO::getAddress);
    Optional<String> postCode = address.map(Address::getPostCode);
    String result = postCode.orElse("우편번호 없음");
    return result;
  }

  //step3)
  //after2
//  public String findPostCode3() {
//    // 그리고 위의 코드를 다음과 같이 축약해서 쓸 수 있다.
//    String result = user.map(UserVO::getAddress)
//        .map(Address::getPostCode)
//        .orElse("우편번호 없음");
//    return result;
//  }

}
