package com.kata.orderinhexagonal.member.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

//TODO - c-b-1-3: email & password validator
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateMemberRequest {
    @Email(message = "유효하지 않은 이메일 형식입니다.",
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+=])[A-Za-z\\d~!@#$%^&*()+=]{8,16}$",
            message = "숫자', '문자', '특수문자'를 1개 이상, 비밀번호는 '최소 8자에서 최대 16자' 안으로 입력해 주세요.")
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;
    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;
    @NotBlank(message = "주소를 입력해 주세요.")
    private String location;
}
