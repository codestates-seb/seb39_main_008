package it.mainPr.controller;

import it.mainPr.dto.bookDto.BookPatchDto;
import it.mainPr.dto.bookDto.BookPostDto;
import it.mainPr.dto.global.SingleResponseDto;
import it.mainPr.mapper.BookMapper;
import it.mainPr.mapper.DiaryMapper;
import it.mainPr.mapper.MemberMapper;
import it.mainPr.model.Book;
import it.mainPr.service.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping
@Slf4j
@Validated
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final BookImageService bookImageService;
    private final DiaryMapper diaryMapper;
    private final DiaryService diaryService;
    private final HeartService heartService;

    @PostMapping("/api/v1/books")
    public ResponseEntity createBook(@Valid @RequestBody BookPostDto bookPostDto) {
        Book book = bookMapper.bookPostDtoToBook(memberService, bookPostDto);
        Book createdBook = bookService.createBook(book);

        return new ResponseEntity<>(
                new SingleResponseDto<>(bookMapper.bookToBookResponseDto(
                        diaryService, diaryMapper, memberMapper, bookImageService, book)), HttpStatus.CREATED);
    }

    @PatchMapping("/api/v1/books/{bookId}")
    public ResponseEntity updateBook(@PathVariable("book_id") Long bookId,
                                     @Valid @RequestBody BookPatchDto bookPatchDto) {

        Book book = bookMapper.bookPatchDtoToBook(bookService, memberService, bookId, bookPatchDto);
        Book updatedBook = bookService.updateBook(book);

        return new ResponseEntity<>(new SingleResponseDto<>(
                bookMapper.bookToBookResponseDto(diaryService, diaryMapper, memberMapper, bookImageService, book)), HttpStatus.OK);
    }

    @GetMapping("/api/v1/books/{bookId}")
    public ResponseEntity findBook(@PathVariable("book_id") Long bookId) {

        Book book = bookService.findVerifiedBook(bookId);
        return new ResponseEntity<>(new SingleResponseDto<>(
                bookMapper.bookToBookAndDiaryResponse(diaryService, heartService, diaryMapper, memberMapper, bookImageService, book)),
                HttpStatus.OK);
    }

    @GetMapping("/api/v1/books")
    public ResponseEntity findBooks(@RequestParam(name = "member_id") Long memberId,
                                    @Positive @RequestParam("page") Integer diaryPage,
                                    @Positive @RequestParam("size") Integer diarySize,
                                    @RequestParam("sort") String diarySort) {

        Book book = bookService.findVerifiedBook(memberId);
        return new ResponseEntity<>(new SingleResponseDto<>(
                bookMapper.bookToBookAndDiaryResponseDto(diaryService, heartService, diaryMapper, memberMapper, bookImageService,
                        book, diaryPage, diarySize, diarySort)), HttpStatus.OK);
    }

    @DeleteMapping("/v1/books/{bookId}")
    public ResponseEntity deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
