package com.hernanurban.bookstore.controller;

import com.hernanurban.bookstore.domain.Book;
import com.hernanurban.bookstore.domain.CreateBookRequest;
import com.hernanurban.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService service){
        this.bookService = service;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
        List<Book> bookEntityList = null;
        if (StringUtils.isEmpty(title)) {
            bookEntityList = bookService.getBooks();
        } else {
            bookEntityList = bookService.getByTitle(title);
        }
        return new ResponseEntity<>(bookEntityList, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookService.search(id), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody CreateBookRequest book) {
        return new ResponseEntity<>(bookService.create(book), HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody CreateBookRequest book) {

        return new ResponseEntity<>(bookService.update(id, book), HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/books/published")
    public ResponseEntity<List<Book>> findByPublished() {
        return new ResponseEntity<>(bookService.findPublished(), HttpStatus.OK);
    }

    @PostMapping("/books/publish/{id}")
    public ResponseEntity publish(@PathVariable("id") Long id) {
       bookService.publish(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
