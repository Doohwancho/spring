package com.jayden.study.querydsl.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"}) //Member(주인):Team = N:1 양방향 관계에서, 양쪽에 toString()걸었을 때, 순환참조 문제 해결하려고 Team 빼고 적용
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this(username, 0);
    }

    public Member(String username, int age) {
        this(username, age, null);
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team) { //주인 쪽에서 하인 더함
        this.team = team; //이 부부이 없으면 FK칸에 null이 뜬다.
        team.getMembers().add(this);
    }
}
