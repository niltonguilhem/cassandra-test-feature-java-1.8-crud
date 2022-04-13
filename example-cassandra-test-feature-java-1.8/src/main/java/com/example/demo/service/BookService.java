package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Iterable<Book> getTestKeySpace() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(UUID id){return bookRepository.findById(id);}

    /*Uma outra forma de fazer Get via ID
    public  Optional<Book> findById(UUID id) {
        try {
            return bookRepository.findById(id);
        } catch (Exception ex){
            return null;
        }
    }*/

    public Book save(Book book){return bookRepository.save(book);}

    /*public  Book insert (Book book) {
        Assert.isNull(book.getId(), "Não foi possível inserir o registro");
        return bookRepository.save(book);
    }*/

    public Book update(Book book, UUID id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        //Busca o item no banco de dados
        Optional<Book> optional = getBookById(id);
        if (optional.isPresent()){
            Book db = optional.get();
            //Copiar as propriedades
            db.setName(book.getName());
            db.setTitle(book.getTitle());
            System.out.println("Item id: " + db.getId());

            //Atualiza item do Book
            bookRepository.save(db);

            return db;

        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(UUID id) {
        Optional<Book> book = getBookById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
        }
    }

}
