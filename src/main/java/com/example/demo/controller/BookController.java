package com.example.demo.controller;


import com.example.demo.FullDemoApplication;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("book")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(FullDemoApplication.class);

    @Autowired
    private BookService bookService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        logger.debug("Creating a book");
        Book createdBook =  bookService.createBook(book);
        logger.info("Book created : "+book.getTitle()+" by "+book.getAuthor());
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getAllBooks(){
        logger.debug("Getting all books");
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "{author}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable("author") String author){
        logger.debug("Getting all books of author : "+author);
        List<Book> books = bookService.getBooksByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @DeleteMapping(value = "{isbn}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> deleteBookByISBN(@PathVariable("isbn") int isbn){
        logger.debug("Delete book request with ISBN : "+isbn);
        bookService.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        logger.debug("Update Book request with ISBN : "+book.getIsbn());
        Book updatedBook = bookService.updateBook(book);
        if (updatedBook == null){
            logger.warn("No Book exists with ISBN : "+book.getIsbn());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

}