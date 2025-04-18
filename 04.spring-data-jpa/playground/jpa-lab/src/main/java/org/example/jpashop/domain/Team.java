package org.example.jpashop.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"}) //Member(주인):Team = N:1 양방향 관계에서, 양쪽에 toString()걸었을 때, 순환참조 문제 해결하려고 Member 빼고 적용
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;
    
    private String name;
    
    @OneToMany(mappedBy = "team")
    List<Member> members = new ArrayList<>();
    
    public Team(String name) {
        this.name = name;
    }
}

