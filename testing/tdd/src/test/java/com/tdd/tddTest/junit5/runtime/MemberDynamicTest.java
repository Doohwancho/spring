package com.tdd.tddTest.junit5.runtime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

/**
 * 단위 테스트의 경우 하나의 Layer 또는 각 기능을 테스트 하는데 중점을 둔다면,
 * 다이나믹 테스트는 실제 고객이 서비스를 이용하는 시나리오를 테스트하는 데에 용이하다.
 *
 *
 * 시나리오
 *
 * 가입 -> 로그인 -> 수정 -> 탈퇴 순으로 테스트를 진행한다.
 */

@SpringBootTest
public class MemberDynamicTest {
//    final String name = "cho";
//    final String email = "cho@gmail.com";
//    final String password = "1234";
//
//    @Disabled("sudo code for 시나리오 테스트 using dynamic test")
//    @TestFactory
//    Stream<DynamicTest> collectionsOfDynamicTest() {
//        return Stream.of(
//                dynamicTest("회원 가입을 진행한다.", () -> {
//                    // given
//                    JoinRequest req = new JoinRequest();
//
//                    // when
//                    JoinResponse res = memberService.join(req);
//
//                    // then
//                    Assertions.assertThat(res.getEmail()).isEqualTo(req.getEmail());
//                    Assertions.assertThat(res.getEmail()).isEqualTo(req.getName());
//                }),
//
//                dynamicTest("로그인을 진행한다.", () -> {
//                    // given
//                    LoginRequest req = new LoginRequest(email, password);
//
//                    // when
//                    AuthInfo authInfo = loginService.login(req);
//
//                    // then
//                    Assertions.assertThat(JWT.decode(authInfo.getToken()))
//                            .getClaim("email").asString())
//                            .isEqualTo(email);
//                }),
//
//                dynamicTest("회원 정보를 수정한다.", () -> {
//                    // given
//                    UpdateRequest req = new UpdateRequest(1L, "cho", "1234", null);
//
//                    // when
//                    memberService.update(req);
//
//                    // then
//                    Assertions.assertThat(member.getName()).isEqualTo(req.getName());
//                    Assertions.assertThat(member.getPassword()).isEqualTo(encode(req.getPassword()));
//                }),
//
//
//                dynamicTest("회원 탈퇴를 진행한다", () -> {
//                    // given
//                    long memberId = 1L;
//
//                    // when
//                    memberService.delete(memberId);
//
//                    // then
//                    Optional<Member> member = memberRepository.findByEmail(email);
//                    Assertions.assertThat(member).isEmpty();
//                })
//        );
//    }
}
