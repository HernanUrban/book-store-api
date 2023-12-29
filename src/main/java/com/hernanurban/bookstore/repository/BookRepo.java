package com.hernanurban.bookstore.repository;

import com.hernanurban.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByPublished(boolean published);
    List<Book> findByTitleContaining(String title);
}