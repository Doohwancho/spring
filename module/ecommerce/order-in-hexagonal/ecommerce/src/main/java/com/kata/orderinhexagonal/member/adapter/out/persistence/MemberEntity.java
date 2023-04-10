package com.kata.orderinhexagonal.member.adapter.out.persistence;


import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "member")
    private List<OrderEntity> orders = new ArrayList<>();

    public MemberEntity(long id, String name, String email, String password, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
    }
}
