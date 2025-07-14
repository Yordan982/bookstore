package com.monevia.bookstore.book_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> streamBooksById(String id);

    Iterable<String> getBooksById(String id);
}