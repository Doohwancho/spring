package com.cho.basic.vo.연관관계매핑;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@ManyToOne 관계, (with Book.java)
//Book : Library = N : 1

//Q. 왜?
//A. 도서관 한 곳에 여러 책이 종속되는데, 한 책이 여러 도서관에 속하진 않으니까.
@Entity
@Getter
@Setter
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String libraryName;


    /**
     * @ManyToOne 양방향
     *
     * Book : Library = N : 1
     * Book 입장에서 @ManyToOne 으로 매핑되어 있다.
     *
     * @ManyToOne
     * @JoinColumn(name = "LIBRARY_ID")
     * private Libarary library;
     *
     * Library 입장에서 @OneToMany 로 매빙되어 있고, mappedBy="library"로 스스로를 매핑시켰다.
     */
//    @OneToMany(mappedBy = "library")
//    private List<Book> books = new ArrayList<>();


    /**
     * @OneToMany 단방향
     *
     * 하지만 이러한 일대다 단방향 형태는 사용을 지양하는데 몇가지 단점이 존재하기 때문이다.
     *
     * 문제점
     * 1. 엔티티가 관리하는 외래키가 반대쪽에 있다. (DB에서는 다쪽에 외래키가 존재하므로 Library가 아닌 Book에 존재하게 된다)
     * 2. 1인 쪽을 저장할 때 N에 대한 update 쿼리가 발생하게 된다.
     *
     * 그러니깐 현재 일(1) 쪽인 서점이 책들(N) 을 갖고 있고 자기가 책들을 관리하겠다고 (관계의 주인이되겠다고) 하는 상황이다. 따라서 @JoinColumn도 서점이 들고 있게 된다.
     *
     */
    @OneToMany
    @JoinColumn(name = "LIBRARY_ID")
    private List<Book> books = new ArrayList<>();



    //util - add books
    public void addBook(Book book){
        books.add(book);
    }
}