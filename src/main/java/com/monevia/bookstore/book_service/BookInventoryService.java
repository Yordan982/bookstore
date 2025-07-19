package com.monevia.bookstore.book_service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookInventoryService {
    private final BookInventoryRepository bookInventoryRepository;
    private final BookRepository bookRepository;

    public BookInventoryService(BookInventoryRepository bookInventoryRepository, BookRepository bookRepository) {
        this.bookInventoryRepository = bookInventoryRepository;
        this.bookRepository = bookRepository;
    }

    public void setBookQuantity(String bookId, BookInventoryDTO inventoryDTO) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException(BookConstants.BOOK_NOT_FOUND));

        BookInventory inventory = bookInventoryRepository.findById(bookId)
                .orElse(new BookInventory(book, 0));

        inventory.setQuantity(inventoryDTO.getQuantity());
        bookInventoryRepository.save(inventory);
    }

    public Integer getBookQuantity(String bookId) {
        return bookInventoryRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException(BookConstants.BOOK_NOT_FOUND)).getQuantity();
    }

    public void addQuantity(String bookId, int quantity) {
        BookInventory inventory = bookInventoryRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException(BookConstants.BOOK_INVENTORY_NOT_FOUND));

        inventory.setQuantity(inventory.getQuantity() + quantity);
        bookInventoryRepository.save(inventory);
    }

    public void removeQuantity(String bookId, int quantity) {
        BookInventory inventory = bookInventoryRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException(BookConstants.BOOK_INVENTORY_NOT_FOUND));

        if (inventory.getQuantity() < quantity) {
            throw new IllegalArgumentException(String.format(
                    BookConstants.QUANTITY_INSUFFICIENT, inventory.getQuantity()));
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);
        bookInventoryRepository.save(inventory);
    }
}