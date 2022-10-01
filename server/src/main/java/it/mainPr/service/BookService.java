package it.mainPr.service;

import it.mainPr.auth.utils.SecurityUtils;
import it.mainPr.dto.bookDto.BookPatchDto;
import it.mainPr.dto.bookDto.BookPostDto;
import it.mainPr.dto.bookDto.BookResponseDto;
import it.mainPr.model.Book;
import it.mainPr.model.Member;
import it.mainPr.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
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

}
