package com.cho.basic.fetchJoin;


import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "team")
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String name;

    public int age;

    @ManyToOne(fetch = FetchType.LAZY)
    public Team team;

    public Member(String name, int age, Team team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }
}
