package org.example.jwt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "sp_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails { // UserDetailService를 구현한 클래스를 따로 분리해서 만들어서 처리해도 된다.

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(User.class);
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
    private LocalDateTime updated; // 수정일

    public void addAuthority(Authority authority){
        log.info("inside addAuthority(), before addAuthority: {}", authorities);
        if(authorities == null) {
            log.info("it was null!");
            authorities = new HashSet<>();
        }

        authorities.add(authority);
        log.info("inside addAuthority(), after authorities: {}", authorities);
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }

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