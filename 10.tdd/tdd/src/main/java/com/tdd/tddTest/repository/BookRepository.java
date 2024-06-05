package com.tdd.tddTest.repository;

import com.tdd.tddTest.domain.book.Book;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface BookRepository {
    List<Book> findNewBooks(int days);
    void save(Book book);
    void saveException(Book book) throws SQLException;
    Book findBookById(String bookId);
    Book findBookByTitleAndPublishedDate(String title, LocalDate localDate);
    Book findBookByTitleAndPriceAndIsDigital(String title, int price, boolean isDigital);
    void saveAll(List<Book> books);
    List<Book> findAllBooks() throws SQLException;
    Collection<Book> findAll();
}
