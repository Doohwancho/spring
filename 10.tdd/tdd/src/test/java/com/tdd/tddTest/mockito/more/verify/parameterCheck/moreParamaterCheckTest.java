package com.tdd.tddTest.mockito.more.verify.parameterCheck;

import com.tdd.tddTest.domain.book.Book;
import com.tdd.tddTest.domain.book.BookRequest;
import com.tdd.tddTest.repository.BookRepository;
import com.tdd.tddTest.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class moreParamaterCheckTest {

    /******************************************************************************************/
    //example1


    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;


    @Test
    public void testSaveBook() {
        //given
        BookRequest bookRequest = new BookRequest("Mockito In Action", 1000, LocalDate.now());
        bookService.addBook(bookRequest);

        //when
        verify(bookRepository).save(bookArgumentCaptor.capture()); //bookRepository.save()가 호출되었을 때, Book을 bookArgumentCaptor에 저장
        Book book = bookArgumentCaptor.getValue();

        //then
        assertEquals("Mockito In Action", book.getTitle());
    }
}
