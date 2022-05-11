package com.testKeySpace.service;

import com.testKeySpace.model.Book;
import com.testKeySpace.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Iterable<Book> getTestKeySpace() { return bookRepository.findAll(); }

    public Optional<Book> getBookById(UUID id){return bookRepository.findById(id);}

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public Book update(Book book, UUID id) {
        Optional<Book> optional = getBookById(id);
        if (optional.isPresent()){
            Book db = optional.get();
            db.setName(book.getName());
            db.setTitle(book.getTitle());
            bookRepository.save(db);
            return db;
        }
        else {
            throw new RuntimeException();
        }
    }

    public void delete(UUID id){
        Optional<Book> book = getBookById(id);
        if (book.isPresent()){
            bookRepository.deleteById(id);
        }
    }
}
