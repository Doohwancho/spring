package com.cho.basic.연관관계매핑.ManyToOne;

import com.cho.basic.연관관계매핑.ManyToOne.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Phone {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private int id;
    @Id
    @Column(name="seq")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int seq;

    @Column(name="number")
    private String number;

    @ManyToOne //targetEntity=User.class, fetch=FetchType.LAZY
    @JoinColumn(name="user_id")
    private User user;

    public Phone(String number){
        this.number = number;
    }
}
