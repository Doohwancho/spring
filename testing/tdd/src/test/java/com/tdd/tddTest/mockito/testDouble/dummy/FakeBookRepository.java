package com.tdd.tddTest.mockito.testDouble.dummy;

import com.tdd.tddTest.domain.book.Book;
import com.tdd.tddTest.repository.BookRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeBookRepository implements BookRepository {
    // In memory database, HashMap or List
    Map<String, Book> bookStore = new HashMap<>();


    @Override
    public void save(Book book) {
        bookStore.put(book.getBookId(), book);
    }

    @Override
    public Collection<Book> findAll() {
        return bookStore.values();
    }



    /*********************
     * Not implemented Mehtods. just ignore this part.
     */

    @Override
    public List<Book> findNewBooks(int days) {
        return null;
    }

    @Override
    public void saveException(Book book) throws SQLException {

    }

    @Override
    public Book findBookById(String bookId) {
        return null;
    }

    @Override
    public Book findBookByTitleAndPublishedDate(String title, LocalDate localDate) {
        return null;
    }

    @Override
    public Book findBookByTitleAndPriceAndIsDigital(String title, int price, boolean isDigital) {
        return null;
    }

    @Override
    public void saveAll(List<Book> books) {

    }

    @Override
    public List<Book> findAllBooks() throws SQLException {
        return null;
    }


}
