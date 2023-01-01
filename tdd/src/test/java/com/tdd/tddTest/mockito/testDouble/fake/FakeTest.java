package com.tdd.tddTest.mockito.testDouble.fake;

import com.tdd.tddTest.domain.book.Book;
import com.tdd.tddTest.mockito.testDouble.dummy.FakeBookRepository;
import com.tdd.tddTest.repository.BookRepository;
import com.tdd.tddTest.service.BookService;
import com.tdd.tddTest.service.BookService2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FakeTest {

    //원래 BookRepository를 implement 받은 FakeBookRepository로 mock 테스트
    @Test
    public void testFake(){
        BookRepository bookRepository = new FakeBookRepository();
        BookService2 bookService = new BookService2(bookRepository);

        bookService.addBook(new Book("1234", "Mockito In Action", 250, LocalDate.now()));
        bookService.addBook(new Book("1235", "JUnit 5 In Action", 200, LocalDate.now()));

        assertEquals(2, bookService.findNumberOfBooks());

    }

    @Test
    public void testFakeWithMockito() {
        BookRepository bookRepository = mock(BookRepository.class);
        BookService2 bookService = new BookService2(bookRepository);

        Book book1 = new Book("1234", "Mockito In Action", 250, LocalDate.now());
        Book book2 = new Book("1235", "JUnit 5 In Action", 200, LocalDate.now());

        Collection<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        when(bookRepository.findAll()).thenReturn(books);

        assertEquals(2, bookService.findNumberOfBooks());
    }
}
