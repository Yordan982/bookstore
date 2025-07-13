package com.monevia.bookstore.book_service;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/book")
@Validated
public class BookController {
    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createBook(
            @RequestBody
            @Valid CreateBookDTO createBookDTO) {
        String newBookId = bookService.createBook(createBookDTO);
        Map<String, String> responseBody = Map.of("id", newBookId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping(value = "/{book_id}/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBookDTO> getBook(
            @PathVariable String book_id) {
        GetBookDTO bookDTO = bookService.getBook(book_id);
        return ResponseEntity.ok(bookDTO);
    }

    @PatchMapping("/{book_id}/update")
    public ResponseEntity<Map<String, String>> updateBook(
            @PathVariable("book_id") String bookId,
            @RequestBody @Valid UpdateBookDTO updateBookDTO) {
        bookService.updateBook(bookId, updateBookDTO);
        Map<String, String> response = Map.of(
                "message", BookConstants.BOOK_UPDATED,
                "book_id", bookId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{book_id}/delete")
    public ResponseEntity<Map<String, String>> deleteBook(
            @PathVariable String book_id) {
        String message = bookService.deleteBook(book_id);
        return ResponseEntity.ok(Map.of("message", message));
    }
}