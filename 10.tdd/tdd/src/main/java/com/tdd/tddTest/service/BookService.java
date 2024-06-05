package com.tdd.tddTest.service;

import com.tdd.tddTest.domain.book.Book;
import com.tdd.tddTest.domain.book.BookRequest;
import com.tdd.tddTest.exception.DatabaseReadException;
import com.tdd.tddTest.exception.DatabaseWriteException;
import com.tdd.tddTest.repository.BookRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BookService {

    private BookRepository bookRepository;
    private EmailService emailService;

    public BookService(BookRepository bookRepository, EmailService emailService) {
        this.bookRepository = bookRepository;
        this.emailService = emailService;
    }

    public void addBook(Book book) {
        if(book.getPrice() <= 500){
            return;
        }
       bookRepository.save(book);
    }

    public void addBookException(Book book) {
        try {
            bookRepository.saveException(book);
        } catch (SQLException e) {
            // log exception
            throw new DatabaseWriteException("Unable to write in database due to - " + e.getMessage());
        }
    }


    public void addBook(BookRequest bookRequest) {
		if(bookRequest.getPrice() <= 500){
			return;
		}
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setPrice(bookRequest.getPrice());
        book.setPublishedDate(bookRequest.getPublishedDate());
        bookRepository.save(book);
    }

    public void addBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    public void updatePrice(String bookId, int updatedPrice){
        if(bookId == null) {
            return;
        }
        Book book = bookRepository.findBookById(bookId);
//		if(book.getPrice() == updatedPrice){
//			return;
//		}
        book.setPrice(updatedPrice);
        bookRepository.save(book);
    }

    public Book getBookByTitleAndPublishedDate(String title, LocalDate localDate) {
        return bookRepository.findBookByTitleAndPublishedDate(title, localDate);
    }

    public Book getBookByTitleAndPriceAndIsDigital(String title, int price, boolean isDigital) {
        return bookRepository.findBookByTitleAndPriceAndIsDigital(title, price, isDigital);
    }

    public List<Book> getNewBooksWithAppliedDiscount(int discountRate, int days){
        List<Book> newBooks = bookRepository.findNewBooks(days); //mocking되는 부분.
        // 500 apply 10% -> 10% of 500 -> 50 -> 500 - 50 -> 450

        //테스트 되는 메인 로직 부분
        for(Book book : newBooks){
            int price = book.getPrice();
            int newPrice = price - (discountRate * price / 100);
            book.setPrice(newPrice);
        }

        return newBooks;
    }

    public int getTotalPriceOfBooks() {
        List<Book> books = null;
        try {
            books = bookRepository.findAllBooks();
        } catch (SQLException e) {
            // log exception
            throw new DatabaseReadException("Unable to read from database due to - " + e.getMessage());
        }
        int totalPrice = 0;
        for(Book book : books){
            totalPrice = totalPrice + book.getPrice();
        }
        return totalPrice;
    }

    public int findNumberOfBooks(){
        return bookRepository.findAll().size();
    }
}