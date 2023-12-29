package com.hernanurban.bookstore.controller;

import com.hernanurban.bookstore.model.Book;
import com.hernanurban.bookstore.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookRepo bookRepo;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
        return new ResponseEntity<>(bookRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        return new ResponseEntity<>(bookRepo.findById(id).orElse(new Book()), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book Book) {
        return new ResponseEntity<>(bookRepo.save(Book), HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        Book bookEntity = bookRepo.findById(id).orElseThrow();
        bookEntity.setTitle(book.getTitle());
        bookEntity.setDescription(book.getDescription());
        bookEntity.setPublished(book.isPublished());
        return new ResponseEntity<>(bookRepo.save(bookEntity), HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
        bookRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/books")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        bookRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/books/published")
    public ResponseEntity<List<Book>> findByPublished() {
        return new ResponseEntity<>(bookRepo.findByPublished(true), HttpStatus.OK);
    }
}
