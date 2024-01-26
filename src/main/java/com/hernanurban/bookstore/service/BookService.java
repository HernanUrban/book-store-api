package com.hernanurban.bookstore.service;

import com.hernanurban.bookstore.converter.BookConverter;
import com.hernanurban.bookstore.domain.Book;
import com.hernanurban.bookstore.domain.CreateBookRequest;
import com.hernanurban.bookstore.entity.BookEntity;
import com.hernanurban.bookstore.repository.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{

    private Logger logger = LoggerFactory.getLogger(BookService.class);

    private BookRepo bookRepo;

    @Autowired
    public BookService (BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }

    @Override
    public Book create(CreateBookRequest book) {
        BookEntity entity = new BookEntity();
        entity.setDescription(book.getDescription());
        entity.setTitle(book.getTitle());
        BookEntity newBook = bookRepo.save(entity);
        logger.info("Book created id " + newBook.getId());
        return BookConverter.convert(newBook);
    }

    @Override
    public void delete(Long bookId) {

    }

    @Override
    public Book search(Long bookId) {
        return BookConverter.convert(bookRepo.findById(bookId).orElseThrow(RuntimeException::new));

    }

    @Override
    public List<Book> getBooks() {
        return bookRepo.findAll().stream().map(BookConverter::convert).toList();
    }

    @Override
    public List<Book> getByTitle(final String title) {
        logger.info("Looking for Book " + title);
        return bookRepo.findByTitleContaining(title).stream().map(BookConverter::convert).toList();
    }

    @Override
    public Book update(Long id, CreateBookRequest book) {
        Optional<BookEntity> entity = bookRepo.findById(id);
        if (entity.isPresent()){
            BookEntity update = entity.get();
            if (book.getTitle()!=null) {
                update.setTitle(book.getTitle());
            }
            if (book.getDescription()!=null) {
                update.setDescription(book.getDescription());
            }
            logger.info("Book updated id " + id);
            return BookConverter.convert(bookRepo.save(update));
        }
        return null;
    }

    @Override
    public List<Book> findPublished() {
        return bookRepo.findByPublished(true).stream().map(BookConverter::convert).toList();
    }

    @Override
    public void publish(Long id) {
        Optional<BookEntity> entity = bookRepo.findById(id);
        BookEntity update = entity.orElseThrow(RuntimeException::new);
        update.setPublished(true);
        bookRepo.save(update);
    }


}
