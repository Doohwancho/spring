package com.tdd.tddTest.mockito.testDouble.dummy;

import com.tdd.tddTest.domain.book.Book;
import com.tdd.tddTest.repository.BookRepository;
import com.tdd.tddTest.service.BookService;
import com.tdd.tddTest.service.EmailService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DummyTest {

    //FakeBookRepository가 BookRepository를 implement받아, 임의의 메서드로 구현됨.
    @Test
    @Disabled
    public void demoDummy(){
        //given
        BookRepository bookRepository = new FakeBookRepository();
        EmailService emailService = new DummyEmailService();
        BookService bookService = new BookService(bookRepository, emailService);

        //when
        bookService.addBook(new Book("1234", "Mockito In Action", 250, LocalDate.now()));
        bookService.addBook(new Book("1235", "JUnit 5 In Action", 200, LocalDate.now()));

        //then
        assertEquals(2, bookService.findNumberOfBooks());
    }

    //이 역시 bookService.findNumberOfBooks() -> bookRepository.findAll() -> FakeBookRepository.findAll() (임의로 개발자가 페이크친 클래스)
    @Test
    public void demoDummyWithMockito() {
        //given
        BookRepository bookRepository = mock(BookRepository.class);
        EmailService emailService = mock(EmailService.class);
        BookService bookService = new BookService(bookRepository, emailService);

        Book book1 = new Book("1234", "Mockito In Action", 250, LocalDate.now());
        Book book2 = new Book("1235", "JUnit 5 In Action", 200, LocalDate.now());


        Collection<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        //when
        when(bookRepository.findAll()).thenReturn(books);

        //then
        assertEquals(2, bookService.findNumberOfBooks());
    }
}
