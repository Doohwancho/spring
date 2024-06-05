package com.practice.domain;

import com.practice.dto.JoinDto;
import lombok.*;

import javax.persistence.*;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.ALL;

@Entity
@Data
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor(access= AccessLevel.PROTECTED)
public class Member { //security에 관리된 User객체라 UserDetails를 받네?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(unique = true) //unique id
    private String username;

    private String password;

    private String intro; //TODO - clear! what is this intro for? A. 회원가입란의 소개란임. 중요한거 아님.

    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Authority> authorities = new HashSet<>();


    private void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public List<String> getRoles() {
        return authorities.stream()
                .map(Authority::getRole)
                .collect(toList());
    }

    public static Member ofUser(JoinDto joinDto) {
        Member member = Member.builder()
                .username(joinDto.getUsername())
                .password(joinDto.getPassword())
                .intro(joinDto.getIntro()) //not necessary
                .build();
        member.addAuthority(Authority.ofUser(member)); //ROLE_USER를 더한다.
        return member;
    }

    public static Member ofAdmin(JoinDto joinDto) {
        Member member = Member.builder()
                .username(joinDto.getUsername())
                .password(joinDto.getPassword())
                .intro(joinDto.getIntro()) //not necessary
                .build();
        member.addAuthority(Authority.ofAdmin(member)); //ROLE_ADMIN을 더한다.
        return member;
    }
}
