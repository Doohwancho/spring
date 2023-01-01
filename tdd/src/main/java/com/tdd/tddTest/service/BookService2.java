package com.tdd.tddTest.service;

import com.tdd.tddTest.domain.book.Book;
import com.tdd.tddTest.repository.BookRepository;

public class BookService2 {
    private BookRepository bookRepository;

    public BookService2(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public int findNumberOfBooks(){
        return bookRepository.findAll().size();
    }
}
