package com.cheese.springjpa.Account;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.Date;

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

    @Email
    @Column(name = "email", nullable = false, unique = true)
    @ApiModelProperty(value = "이메일")
    private String email;

    @Column(name = "first_name", nullable = false)
    @ApiModelProperty(value = "이름")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @ApiModelProperty(value = "성")
    private String lastName;

    @Column(name = "password", nullable = false)
    @ApiModelProperty(value = "비밀번호")
    private String password;

    @Column(name = "address1", nullable = false)
    @ApiModelProperty(value = "주소1")
    private String address1;

    @Column(name = "address2", nullable = false)
    @ApiModelProperty(value = "주소2")
    private String address2;

    @Column(name = "zip", nullable = false)
    @ApiModelProperty(value = "집코드")
    private String zip;

    @Column(name = "created_at")
//    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
//    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    @Builder
    public Account(String email, String firstName, String lastName, String password, String address1, String address2, String zip) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address1 = address1;
        this.address2 = address2;
        this.zip = zip;
    }

    public void updateMyAccount(AccountDto.MyAccountReq dto) {
        this.address1 = dto.getAddress1();
        this.address2 = dto.getAddress2();
        this.zip = dto.getZip();
    }
}
