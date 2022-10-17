package com.cheese.springjpa.Account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class AccountDto {
    /*

    VO안쓰고 DTO로 분리하는 이유: case에 따른 파라미터 커스텀
    SignUpReq, MyAccountReq, Res 분리.

     */

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ApiModel(value = "회원 정보", description = "아이디, 이름, 비밀번호, 이메일, 주소, 가입날짜를 가진 Domain Class")
    public static class SignUpReq {

        @Email
        @ApiModelProperty(value = "이메일", example = "mail@gmail.com", required = true)
        private String email;

        @NotEmpty
        @ApiModelProperty(value = "이름", example = "doohwan", required = true)
        private String firstName;

        @NotEmpty
        @ApiModelProperty(value = "성", example = "cho", required = true)
        private String lastName;

        @NotEmpty
        @ApiModelProperty(value = "비밀번호", example = "1234", required = false)
        private String password;

        @NotEmpty
        @ApiModelProperty(value = "주소1", example = "address1", required = true)
        private String address1;

        @NotEmpty
        @ApiModelProperty(value = "주소2", example = "address2", required = true)
        private String address2;

        @NotEmpty
        @ApiModelProperty(value = "집코드", example = "zip code", required = true)
        private String zip;

        @Builder
        public SignUpReq(String email, String firstName, String lastName, String password, String address1, String address2, String zip) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
            this.address1 = address1;
            this.address2 = address2;
            this.zip = zip;
        }

        public Account toEntity() {
            return Account.builder()
                    .email(this.email)
                    .firstName(this.firstName)
                    .lastName(this.lastName)
                    .password(this.password)
                    .address1(this.address1)
                    .address2(this.address2)
                    .zip(this.zip)
                    .build();
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MyAccountReq {
        private String address1;
        private String address2;
        private String zip;

        @Builder
        public MyAccountReq(String address1, String address2, String zip) {
            this.address1 = address1;
            this.address2 = address2;
            this.zip = zip;
        }

    }

    @Getter
    public static class Res {
        private String email;
        private String firstName;
        private String lastName;
        private String address1;
        private String address2;
        private String zip;

        public Res(Account account) {
            this.email = account.getEmail();
            this.firstName = account.getFirstName();
            this.lastName = account.getLastName();
            this.address1 = account.getAddress1();
            this.address2 = account.getAddress2();
            this.zip = account.getZip();
        }
    }
}
