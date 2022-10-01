package com.cho.security.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.cho.security.common.CommonVO;
import com.cho.security.enums.role.UserRole;

import java.io.Serializable;


@NoArgsConstructor
@Entity
@Table(name = "USER")
@Getter
@Setter
public class UserVO extends CommonVO implements Serializable {

    @Setter
    @Column(nullable = false, unique = true, length = 50)
    private String userEmail;

    @Setter
    @Column(nullable = false)
    private String userPw;

    @Setter
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder
    public UserVO(String userEmail, String userPw){
        this.userEmail = userEmail;
        this.userPw = userPw;
    }

}
