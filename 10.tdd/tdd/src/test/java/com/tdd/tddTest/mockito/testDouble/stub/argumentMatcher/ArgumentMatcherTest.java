package com.tdd.tddTest.mockito.testDouble.stub.argumentMatcher;

import com.tdd.tddTest.domain.book.Book;
import com.tdd.tddTest.repository.BookRepository;
import com.tdd.tddTest.service.BookService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ArgumentMatcherTest {

    /*
        * ArgumentMatcher
        *
        * any(), eq() 같은 애들
     */

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ArgumentMatcherTest.class);

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testUpdatePrice() {
        //given
        Book book1 = new Book("1234", "Mockito In Action", 600, LocalDate.now());
        Book book2 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        when(bookRepository.findBookById(any(String.class))).thenReturn(book1);

        //when
        bookService.updatePrice("1234", 500);

        //then
        verify(bookRepository).save(book1);
        assertEquals(book1.getPrice(), 500);
    }

    //TODO - does not understand why it still throws NPE. maybe problem with eq()?
    //eq()은 특정 값(object 포함)이 정확히 매치되어야 할 때 사용.
    //any(String.class) might also work, if param could be generalized
    //anyString() might also work
    @Test
    @Disabled
    public void testInvalidUseOfArgumentMatchers() {
        //given
        Book book = new Book("1234", "Mockito", 600, LocalDate.now());
        when(bookRepository.findBookByTitleAndPublishedDate(eq("Mockito"), any())).thenReturn(book); //localDate를 any()해서 언제 테스트해도 상관 없게끔

        //when
        Book actualBook = bookService.getBookByTitleAndPublishedDate(eq("Mockito"), any());

        //then
        log.info(actualBook.toString());
        assertEquals("Mockito In Action", actualBook.getTitle());
    }



    @Test
    public void testSpecificTypeOfArgumentMatchers() {
        //given
        Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
        when(bookRepository.findBookByTitleAndPriceAndIsDigital(anyString(), anyInt(), anyBoolean())).thenReturn(book);

        //when
        Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("Mockito In Action", 600, true);

        //then
        assertEquals("Mockito In Action", actualBook.getTitle());
    }

    @Test
    public void testCollectionTypeArgumentMatchers() {
        //given
        List<Book> books = new ArrayList<>();
        Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
        books.add(book);

        //when
        bookService.addBooks(books);

        //then
        verify(bookRepository).saveAll(anyList()); // anySet, anyMap, anyCollection
    }

    @Test
    public void testStringTypeArgumentMatchers() {
        //given
        Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
        when(bookRepository.findBookByTitleAndPriceAndIsDigital(contains("Action"), anyInt(), anyBoolean())).thenReturn(book);

        //when
        Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("JUnit 5 In Action", 600, true);

        //then
        assertEquals("Mockito In Action", actualBook.getTitle());
    }
}
