package com.monevia.bookstore.book_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/book/{book_id}/inventory")
public class BookInventoryController {
    private final BookInventoryService bookInventoryService;

    public BookInventoryController(BookInventoryService bookInventoryService) {
        this.bookInventoryService = bookInventoryService;
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> getQuantity(
            @PathVariable(name = "book_id") String bookId) {
        Map<String, String> response = Map.of("quantity",
                String.valueOf(bookInventoryService.getBookQuantity(bookId)));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/set")
    public ResponseEntity<Map<String, String>> setQuantity(
            @PathVariable(name = "book_id") String bookId,
            @RequestBody BookInventoryDTO bookInventoryDTO) {
        bookInventoryService.setBookQuantity(bookId, bookInventoryDTO);

        Map<String, String> response = Map.of(
                "message", String.format(BookConstants.QUANTITY_SET,
                        bookInventoryDTO.getQuantity()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addQuantity(
            @PathVariable(name = "book_id") String bookId,
            @RequestBody BookInventoryDTO bookInventoryDTO) {
        bookInventoryService.addQuantity(bookId, bookInventoryDTO.getQuantity());
        Map<String, String> response = Map.of(
                "message", String.format(BookConstants.QUANTITY_ADDED,
                        bookInventoryDTO.getQuantity()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/remove")
    public ResponseEntity<Map<String, String>> removeQuantity(
            @PathVariable(name = "book_id") String bookId,
            @RequestBody BookInventoryDTO bookInventoryDTO) {
        bookInventoryService.removeQuantity(bookId, bookInventoryDTO.getQuantity());
        Map<String, String> response = Map.of(
                "message", String.format(BookConstants.QUANTITY_REMOVED,
                        bookInventoryDTO.getQuantity()));
        return ResponseEntity.ok(response);
    }
}