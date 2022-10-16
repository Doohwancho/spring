package com.cheese.swagger.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AccountDto {


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ApiModel(value = "회원 정보", description = "아이디, 이름, 비밀번호, 이메일, 주소, 가입날짜를 가진 Domain Class")
    public static class SignUpReq {
        @ApiModelProperty(value = "이메일")
        private String email;
        @ApiModelProperty(value = "이름")
        private String firstName;
        @ApiModelProperty(value = "성")
        private String lastName;
        @ApiModelProperty(value = "비밀번호")
        private String password;
        @ApiModelProperty(value = "주소1")
        private String address1;
        @ApiModelProperty(value = "주소2")
        private String address2;
        @ApiModelProperty(value = "집코드")
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
                    .fistName(this.firstName)
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
