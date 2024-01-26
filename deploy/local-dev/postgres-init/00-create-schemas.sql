CREATE DATABASE bookstoredb;
\connect bookstoredb;
CREATE SCHEMA books;
ALTER DATABASE bookstoredb SET search_path TO books,public;