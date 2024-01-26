package com.hernanurban.bookstore.service;

import com.hernanurban.bookstore.domain.Book;
import com.hernanurban.bookstore.domain.CreateBookRequest;
import com.hernanurban.bookstore.entity.BookEntity;

import java.util.List;

public interface IBookService {

    public Book create(CreateBookRequest book);

    public void delete(Long bookId);

    public Book search(Long bookId);

    public List<Book> getBooks();

    public List<Book> getByTitle(String title);

    Book update(Long id, CreateBookRequest book);

    List<Book> findPublished();

    public void publish(Long id);
}
