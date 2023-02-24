package com.tdd.tddTest.mockito.more.exceptionHandling;

import com.tdd.tddTest.domain.book.Book;
import com.tdd.tddTest.exception.DatabaseReadException;
import com.tdd.tddTest.exception.DatabaseWriteException;
import com.tdd.tddTest.repository.BookRepository;
import com.tdd.tddTest.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ExceptionHandlingMockitoTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testTotalPriceOfBooks() throws SQLException {
        //case1
        when(bookRepository.findAllBooks()).thenThrow(SQLException.class);

        //case2
//        when(bookRepository.findAllBooks()).thenThrow(new SQLException("Database not available"));
        assertThrows(DatabaseReadException.class, () -> bookService.getTotalPriceOfBooks());
    }

    @Test
    public void testAddBook() throws SQLException {
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        doThrow(SQLException.class).when(bookRepository).saveException(book);
        assertThrows(DatabaseWriteException.class, () -> bookService.addBookException(book));
    }
}
