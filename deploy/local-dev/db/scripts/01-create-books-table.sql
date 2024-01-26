--liquibase formatted sql

--changeset bookstoredb:create-books-table
CREATE TABLE books (
                       id BIGSERIAL PRIMARY KEY,
                       title TEXT NOT NULL UNIQUE,
                       description TEXT,
                       published BOOLEAN
);
--rollback DROP TABLE books;