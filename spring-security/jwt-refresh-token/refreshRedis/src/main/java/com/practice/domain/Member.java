package com.practice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.model.MemberModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails { //security에 관리된 User객체라 UserDetails를 받네?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String intro; //TODO - clear! what is this intro for? A. 회원가입란의 소개란임. 중요한거 아님.

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    //TODO - implements UserDetails 했기 때문에, 구현해야하는 메서드들.
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return false;
    }

    public static Member from(MemberModel memberModel) {
        return Member.builder()
                .username(memberModel.getUsername())
                .password(memberModel.getPassword())
                .intro(memberModel.getIntro())
                .build();
    }
}
