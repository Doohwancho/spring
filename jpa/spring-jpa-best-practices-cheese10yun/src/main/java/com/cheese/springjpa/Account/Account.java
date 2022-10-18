package com.cheese.springjpa.Account;

import com.cheese.springjpa.Account.model.Address;
import com.cheese.springjpa.Account.model.Email;
import com.cheese.springjpa.Account.model.Password;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Getter //no @Setter. builder pattern으로 객체 생성.
@NoArgsConstructor(access = AccessLevel.PROTECTED) //객체의 직접생성을 외부에서 못하게 설정
@ApiModel(value = "회원 정보", description = "아이디, 이름, 비밀번호, 이메일, 주소, 가입날짜를 가진 Domain Class") //for swagger2
public class Account {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "아이디")
    private long id;

//    @Email
//    @Column(name = "email", nullable = false, unique = true)
//    @ApiModelProperty(value = "이메일")
//    private String email;
    @Embedded
    private com.cheese.springjpa.Account.model.Email email;

    @Column(name = "first_name", nullable = false)
    @ApiModelProperty(value = "이름")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @ApiModelProperty(value = "성")
    private String lastName;

//    @Column(name = "password", nullable = false)
//    @ApiModelProperty(value = "비밀번호")
//    private String password;

    @Embedded
    private Password password;

//    @Column(name = "address1", nullable = false)
//    @ApiModelProperty(value = "주소1")
//    private String address1;

//    @Column(name = "zip", nullable = false)
//    @ApiModelProperty(value = "집코드")
//    private String zip;


    @Embedded
    private Address address;

//    @Column(name = "address2", nullable = false)
//    @ApiModelProperty(value = "주소2")
//    private String address2;

//    @Embedded
//    private com.cheese.springjpa.common.model.DateTime dateTime;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "update_at", nullable = false, updatable = false)
    private LocalDateTime updatedAt;


//    @Column(name = "created_at")
////    @Temporal(TemporalType.TIMESTAMP)
//    @CreationTimestamp
//    private Date createdAt;
//
//    @Column(name = "updated_at")
////    @Temporal(TemporalType.TIMESTAMP)
//    @UpdateTimestamp
//    private Date updatedAt;

    @Builder
    public Account(Email email, String firstName, String lastName, Password password, Address address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
    }

    public void updateMyAccount(AccountDto.MyAccountReq dto) {
        this.address = dto.getAddress();
    }
}
