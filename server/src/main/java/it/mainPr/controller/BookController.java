package it.mainPr.controller;

import it.mainPr.dto.bookDto.BookPatchDto;
import it.mainPr.dto.bookDto.BookPostDto;
import it.mainPr.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/v1/books")
    public ResponseEntity createBook(@RequestBody BookPostDto bookPostDto) {

        return ResponseEntity.ok(bookService.createBook(bookPostDto));
    }

    @PatchMapping("/v1/books/{bookId}")
    public ResponseEntity updateBook(@RequestBody BookPatchDto bookPatchDto, @PathVariable Long bookId) {

        return ResponseEntity.ok(bookService.updateBook(bookPatchDto,bookId));
    }

    @GetMapping("/v1/books/{bookId}")
    public ResponseEntity findBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBook(bookId));
    }

    @GetMapping("/v1/books")
    public ResponseEntity findBooks(@RequestParam(name = "member-id") Long memberId) {
        return ResponseEntity.ok(bookService.getBooks(memberId));
    }

    @DeleteMapping("/v1/books/{bookId}")
    public ResponseEntity deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
