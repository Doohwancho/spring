package com.cho.basic.vo.연관관계매핑;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

//@OneToMany 관계, (with Member.java)
//Member : Book = 1 : N

//Q. 왜?
//A. 멤버 1명당 여러 책을 빌릴 수 있으니까.

//Q. 어? 하나의 책은 여러명한테 빌려질 수 있지 않나?
//A. 아님. 한 책이 Member1에게 대출받아지면, 나머지 Member2,3,4,... 은 빌릴 수 없잖아.

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    String name;

    @Column
    String title;

    /**
     * @ManyToOne 단방향 with library
     *
     * 도서관 하나에 여러 책이 종속되지만, 하나의 책이 여러 도서관에 종속되진 않으니까.
     *
     * 1:N일 때, 항상 N쪽이 1의 외래키를 가지고 있다.
     * Book : Library = N : 1 이니까, @ManyToOne이고, N쪽인 Book이 Library의 FK를 관리한다.
     */
    @ManyToOne
    @JoinColumn(name = "LIBRARY_ID")
    private Library library;
}
