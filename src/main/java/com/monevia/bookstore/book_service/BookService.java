package com.monevia.bookstore.book_service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookInventoryRepository bookInventoryRepository;

    public BookService(BookRepository bookRepository, BookMapper bookMapper, BookInventoryRepository bookInventoryRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.bookInventoryRepository = bookInventoryRepository;
    }

    @Transactional
    public String createBook(CreateBookDTO bookDTO) {
        Book book = new Book(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPrice(), bookDTO.getGenre());
        BookInventory inventory = new BookInventory(book, bookDTO.getQuantity());
        bookRepository.save(book);
        bookInventoryRepository.save(inventory);
        return book.getId();
    }

    public GetBookDTO getBook(String bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalArgumentException(BookConstants.BOOK_NOT_FOUND));
        return new GetBookDTO(book.getTitle(), book.getPrice());
    }

    public void updateBook(String bookId, UpdateBookDTO updateBookDTO) {
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalArgumentException(BookConstants.BOOK_NOT_FOUND));
        bookMapper.updateBookFromDTO(updateBookDTO, book);
        bookRepository.save(book);
    }

    public String deleteBook(String bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new IllegalArgumentException(BookConstants.BOOK_NOT_FOUND);
        }
        bookRepository.deleteById(bookId);
        return BookConstants.BOOK_DELETED;
    }
}