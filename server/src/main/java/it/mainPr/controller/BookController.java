package it.mainPr.controller;

import it.mainPr.dto.bookDto.BookPostDto;
import it.mainPr.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/v1/books")
    public ResponseEntity createBook(BookPostDto bookPostDto) {

        return ResponseEntity.ok(bookService.createBook(bookPostDto));
    }
}
