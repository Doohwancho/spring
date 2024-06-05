package com.tdd.tddTest.mockito.more.testMainLogicMockRest;

import com.tdd.tddTest.domain.book.Book;
import com.tdd.tddTest.repository.BookRepository;
import com.tdd.tddTest.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CheckMainLogicOnServiceLayer {

    @InjectMocks
    private BookService bookService; //서비스안에 메인로직을 테스트 하고 싶다.

    @Mock
    private BookRepository bookRepository; //서비스 안에 메인 로직 말고, DB io는 mocking

    @Test
    public void demoCreateMocksUsingAnnotations() {
        Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        Book book2 = new Book("1235", "JUnit 5 In Action", 400, LocalDate.now());

        List<Book> newBooks = new ArrayList<>();
        newBooks.add(book1);
        newBooks.add(book2);

        when(bookRepository.findNewBooks(7)).thenReturn(newBooks); //db io는 내가 원하는 input으로 mocking

        List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7); //메인 로직만 테스트

        assertEquals(2, newBooksWithAppliedDiscount.size());
        assertEquals(450, newBooksWithAppliedDiscount.get(0).getPrice()); //메인로직 대로 결과가 나왔는지 확인
        assertEquals(360, newBooksWithAppliedDiscount.get(1).getPrice());
    }
}
