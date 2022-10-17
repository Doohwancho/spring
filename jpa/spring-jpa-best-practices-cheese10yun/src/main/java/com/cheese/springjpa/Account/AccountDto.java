package com.cheese.springjpa.Account;

import com.cheese.springjpa.Account.model.Address;
import com.cheese.springjpa.Account.model.Password;
import com.cheese.springjpa.Account.model.Email;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;

public class AccountDto {
    /*

    VO안쓰고 DTO로 분리하는 이유: case에 따른 파라미터 커스텀
    SignUpReq, MyAccountReq, Res 분리.

     */

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ApiModel(value = "회원 정보", description = "아이디, 이름, 비밀번호, 이메일, 주소, 가입날짜를 가진 Domain Class")
    public static class SignUpReq {

        @Valid
        @ApiModelProperty(value = "이메일", example = "mail@gmail.com", required = true)
        private Email email;

        @NotEmpty
        @ApiModelProperty(value = "이름", example = "doohwan", required = true)
        private String firstName;

        @NotEmpty
        @ApiModelProperty(value = "성", example = "cho", required = true)
        private String lastName;


        @ApiModelProperty(value = "비밀번호", example = "1234", required = false)
        private String password;


        @Valid
        private Address address;

//        @NotEmpty
//        @ApiModelProperty(value = "주소1", example = "address1", required = true)
//        private String address1;
//
//        @NotEmpty
//        @ApiModelProperty(value = "주소2", example = "address2", required = true)
//        private String address2;
//
//        @NotEmpty
//        @ApiModelProperty(value = "집코드", example = "zip code", required = true)
//        private String zip;

        @Builder
        public SignUpReq(Email email, String fistName, String lastName, String password, Address address) {
            this.email = email;
            this.firstName = fistName;
            this.lastName = lastName;
            this.password = password;
            this.address = address;
        }

        public Account toEntity() {
            return Account.builder()
                    .email(this.email)
                    .firstName(this.firstName)
                    .lastName(this.lastName)
                    .password(Password.builder().value(this.password).build())
                    .address(this.address)
                    .build();
        }

    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MyAccountReq {
        private Address address;

        @Builder
        public MyAccountReq(final Address address) {
            this.address = address;
        }

    }

    @Getter
    public static class Res {
        private com.cheese.springjpa.Account.model.Email email;
        private Password password;
        private String firstName;
        private String lastName;
        private Address address;

        public Res(Account account) {
            this.email = account.getEmail();
            this.firstName = account.getFirstName();
            this.lastName = account.getLastName();
            this.address = account.getAddress();
            this.password = account.getPassword();
        }
    }
}
