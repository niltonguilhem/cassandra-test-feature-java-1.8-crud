package com.testKeySpace.controller;

import com.testKeySpace.model.Book;
import com.testKeySpace.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/testKeySpace")
public class testKeySpaceController {

    @Autowired
    private BookService bookService;

    @GetMapping()
    public Iterable<Book> get(){
        return bookService.getTestKeySpace();
    }

    @GetMapping("/{id}")
    public Optional<Book> get (@PathVariable("id") UUID id ) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> put (@PathVariable ("id") UUID id, @RequestBody Book book){
        Book b = bookService.update(book,id);
        return new ResponseEntity<>(b, HttpStatus.ALREADY_REPORTED);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable ("id") UUID id) {
        bookService.delete(id);
    }
}
