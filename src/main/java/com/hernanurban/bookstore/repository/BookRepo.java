package com.hernanurban.bookstore.repository;

import com.hernanurban.bookstore.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByPublished(boolean published);
    List<BookEntity> findByTitleContaining(String title);
}