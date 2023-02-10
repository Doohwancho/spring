package com.cho.basic.연관관계매핑;

import com.cho.basic.repository.BookRepository;
import com.cho.basic.repository.LibraryRepository;
import com.cho.basic.repository.MemberRepository;
import com.cho.basic.vo.Member;
import com.cho.basic.vo.연관관계매핑.Book;
import com.cho.basic.vo.연관관계매핑.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class 연관관계_테스트 {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager em;
//    private EntityTransaction transaction;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    @BeforeEach
    void setUp() {
//        entityManager = entityManagerFactory.createEntityManager();
//        transaction = entityManager.getTransaction();
        em = entityManagerFactory.createEntityManager();
    }

    @Test
    public void LibrarySave() throws Exception
    {
        //given
        Book book1 = new Book();
        book1.setName("THIS IS BOOK1");

        Book book2 = new Book();
        book2.setName("THIS IS BOOK2");

        Library library = new Library();
        library.setLibraryName("ABC LIBRARY");

        //when
        System.out.println("================QUERY START================");
        bookRepository.save(book1);
        bookRepository.save(book2);
        System.out.println("================QUERY END================\n");

        library.addBook(book1);
        library.addBook(book2);
        System.out.println("================QUERY START================");
        libraryRepository.save(library);
        System.out.println("================QUERY END================\n");

        entityManagerFactory.close();
//        entityManager.flush();

        entityManagerFactory.createEntityManager();
        //then
    }
}
