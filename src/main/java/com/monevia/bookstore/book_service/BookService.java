package com.monevia.bookstore.book_service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String createBook(CreateBookDTO bookDTO) {
        Book book = new Book(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPrice(), bookDTO.getGenre());
        bookRepository.save(book);
        return book.getId();
    }

    public GetBookDTO getBook(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, BookConstants.BOOK_NOT_FOUND));

        return new GetBookDTO(book.getTitle(), book.getPrice());
    }

    public String deleteBook(String id) {
        if (!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, BookConstants.BOOK_NOT_FOUND);
        }
        bookRepository.deleteById(id);
        return BookConstants.BOOK_DELETED;
    }
}