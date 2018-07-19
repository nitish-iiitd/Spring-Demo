package com.example.demo.service;

import com.example.demo.model.Book;

import java.util.List;

public interface BookService {

    Book createBook(Book book);

    List<Book> getAllBooks();

    List<Book> getBooksByAuthor(String author);

//    Book getBookByISBN(int isbn);

    Book updateBook(Book book);

    void deleteBook(int isbn);

}