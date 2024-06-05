package com.sp.sec2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Document(collection = "sp_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails { // UserDetailService를 구현한 클래스를 따로 분리해서 만들어서 처리해도 된다.

    @Id
    private String userId;
    @Indexed(unique = true) //mongo db에서 unique하게 유지하기 위해 붙임.
    private String email;
    private String name;
    private String picUrl;
    @JsonIgnore
    private String password;
    private String role;
    private boolean enabled;

    private Set<Authority> authorities;

    private LocalDateTime created; // 생성일
    private LocalDateTime updated; // 수정일 TODO - j-b-1: 유저 관련 제어할 떄, 중간에 updated시간을 변경해야 함.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

    //TODO - j-b-1: account 만료일, lock 여부, credential expired 여부를 보두 enabled로 처리.
    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

}
