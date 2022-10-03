package it.mainPr.service;

import it.mainPr.auth.utils.SecurityUtils;
import it.mainPr.dto.bookDto.BookPatchDto;
import it.mainPr.dto.bookDto.BookPostDto;
import it.mainPr.dto.bookDto.BookResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.model.Book;
import it.mainPr.model.Diary;
import it.mainPr.model.Member;
import it.mainPr.repository.BookRepository;

import it.mainPr.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public BookResponseDto createBook(BookPostDto bookPostDto) {
        String memberEmail = SecurityUtils.getCurrentMemberEmail();
        Member member = memberService.findVerifiedMember(memberEmail);

        Book newBook = Book.builder()
                .bookTitle(bookPostDto.getBookTitle())
                .bookSubTitle(bookPostDto.getBookSubTitle())
                .bookImageUrl(bookPostDto.getBookImageUrl())
                .build();

        newBook.setMember(member);
        bookRepository.save(newBook);

        return BookResponseDto.of(newBook);
    }

    public BookResponseDto updateBook(BookPatchDto bookPatchDto, long bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.DIARY_NOT_FOUND));

        checkPermission(book);

        Optional.ofNullable(bookPatchDto.getBookTitle())
                .ifPresent(bookTitle -> book.setBookTitle(bookTitle));
        Optional.ofNullable(bookPatchDto.getBookSubTitle())
                .ifPresent(bookSubTitle -> book.setBookSubTitle(bookSubTitle));
        Optional.ofNullable(bookPatchDto.getBookImageUrl())
                .ifPresent(bookImageUrl -> book.setBookImageUrl(bookImageUrl));
        bookRepository.save(book);

        return BookResponseDto.of(book);
    }

    public BookResponseDto getBook(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.BOOK_NOT_FOUND));

        return BookResponseDto.of(book);
    }

    public List<BookResponseDto> getBooks(long memberId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));

        List<Book> books =  bookRepository.findByMember(member).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.BOOK_NOT_FOUND));

        List<BookResponseDto> listBooks = new ArrayList<BookResponseDto>(books.size());
        for(Book book : books) {
            listBooks.add(BookResponseDto.of(book));
        }

        return listBooks;
    }

    public void deleteBook(long bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.BOOK_NOT_FOUND));
        checkPermission(book);

        bookRepository.deleteById(bookId);
    }

    public void checkPermission(Book book){
        String memberEmail = SecurityUtils.getCurrentMemberEmail();
        Member member = memberService.findVerifiedMember(memberEmail);

        if(member.getMemberId() != book.getMember().getMemberId()) {
            throw new BusinessLogicalException(ExceptionCode.BOOK_NOT_FOUND);
        }
    }
}
