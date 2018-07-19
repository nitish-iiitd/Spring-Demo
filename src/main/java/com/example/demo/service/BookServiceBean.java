package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceBean implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book){ return bookRepository.save(book);}

    @Override
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public void deleteBook(int isbn){bookRepository.deleteById(isbn);}

    @Override
    public List<Book> getBooksByAuthor(String author){return bookRepository.findBooksByAuthor(author);}

//    @Override
//    public Book getBookByISBN(int isbn){return bookRepository.findById(isbn);}

    @Override
    public Book updateBook(Book book){
        if (book.getIsbn() == 0) {
            return null;
        }
        Book updateBook = bookRepository.findById(book.getIsbn()).orElse(null);
        updateBook.setAuthor(book.getAuthor());
        updateBook.setTitle(book.getTitle());
        bookRepository.save(updateBook);
        return updateBook;
    }

}