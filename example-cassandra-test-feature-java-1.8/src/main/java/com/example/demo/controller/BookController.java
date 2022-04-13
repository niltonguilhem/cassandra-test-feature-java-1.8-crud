package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping()
    public Iterable<Book> get(){
        return bookService.getTestKeySpace();
    }

    /* Uma outra forma de realizar GetMapping("/{id}")
    @GetMapping("/{id}")
    public Book getById(@PathVariable("id")UUID id){
        return bookService.findById(id);
    }*/

    @GetMapping("/{id}")
    public Optional<Book> get (@PathVariable("id") UUID id ) {
        return bookService.getBookById(id);
    }

    /*@PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }*/

    @PostMapping
    public  String post(@RequestBody Book book){
        Book b =bookService.save(book);

        return "Dado salvo com sucesso: " + b.getId();
    }


    @PutMapping("/{id}")
    public String put(@PathVariable("id") UUID id, @RequestBody Book book) {
        Book b = bookService.update(book, id);

        return  "Item atualizado com sucesso: "+ b.getId();
    }

    @DeleteMapping("/{id}")
    public  String delete(@PathVariable("id") UUID id) {

        bookService.delete(id);

        return "Item deletado com sucesso";
    }

}
