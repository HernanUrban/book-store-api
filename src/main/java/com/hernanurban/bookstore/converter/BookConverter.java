package com.hernanurban.bookstore.converter;

import com.hernanurban.bookstore.domain.Book;
import com.hernanurban.bookstore.entity.BookEntity;

public class BookConverter {

    public static BookEntity convert(Book book) {
        BookEntity entity = new BookEntity();
        entity.setDescription(book.getDescription());
        entity.setTitle(book.getTitle());
        entity.setId(book.getId());
        entity.setPublished(book.isPublished());
        return entity;
    }

    public static Book convert(BookEntity entity) {
        Book book = new Book();
        book.setId(entity.getId());
        book.setTitle(entity.getTitle());
        book.setDescription(entity.getDescription());
        book.setPublished(entity.isPublished());
        return book;
    }
}
