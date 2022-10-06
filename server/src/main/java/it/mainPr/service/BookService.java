package it.mainPr.service;

import it.mainPr.auth.utils.SecurityUtils;
import it.mainPr.dto.bookDto.BookPatchDto;
import it.mainPr.dto.bookDto.BookPostDto;
import it.mainPr.dto.bookDto.BookResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.model.*;
import it.mainPr.repository.BookRepository;

import it.mainPr.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final MemberService memberService;

    @Transactional
    public Book createBook(Book book) {

        verifyExistBook(book.getBookTitle());

        return bookRepository.save(book);
    }

    public void verifyExistBook(String bookTitle) {//이미 등록된 가게인지 확인
        Optional<Book> book = bookRepository.findByBookTitleAndBookStatus(
                bookTitle, Book.BookStatus.BOOK_EXIST
        );
        if (book.isPresent()) //이미 등록된 가게면 에러!
            throw new BusinessLogicalException(ExceptionCode.BOOK_EXIST);
    }

    public void verifyExistBookThenCheckWriter(Book book1) {//이미 등록된 가게인지 확인하고 이미 등록한 가게가 있다면,
        // 해당 등록한 가게의 주인이 아니면 예외 발생,
        Optional<Book> book = bookRepository.findByBookTitleAndBookStatus(
                book1.getBookTitle(), Book.BookStatus.BOOK_EXIST
        );
        if (book.isPresent()) { //이미 등록된 가게면
            if (book.get().getMember() != book1.getMember())// 해당 등록한 가게의 주인이 아니면 예외 발생,
                throw new BusinessLogicalException(ExceptionCode.BOOK_EXIST);
        }
    }

    public Book findVerifiedBook(long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        Book findBook = optionalBook.orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.BOOK_NOT_FOUND));

        if (findBook.getBookStatus() == Book.BookStatus.BOOK_NOT_EXIST) {
            throw new BusinessLogicalException(ExceptionCode.BOOK_NOT_FOUND);
        }
        return findBook;
    }

    public Member findMemberAtBook(long bookId) {
        Book findBook = findVerifiedBook(bookId);
        return findBook.getMember();
    }

    @Transactional
    public Book updateBook(Book book) {

        verifyExistBookThenCheckWriter(book);

        Book findBook = findVerifiedBook(book.getBookId());

        Optional.ofNullable(book.getModifiedAt())//업데이트 날짜 수정
                .ifPresent(bookModifiedAt -> findBook.setModifiedAt(bookModifiedAt));

        Optional.ofNullable(book.getBookImgUrl())
                .ifPresent(bookImgUrls -> { //BookImages null값 아닐때!
                    findBook.getBookImgUrl().stream().forEach(bookImgUrl ->
                            bookImgUrl.setBookImageStatus(BookImage.BookImageStatus.BOOK_IMAGE_NOT_EXIST));

                    book.getBookImgUrl().stream().forEach(bookImage -> //새 스토어 이미지로 갱신
                            findBook.getBookImgUrl().add(bookImage));
                });

        Optional.ofNullable(book.getBookTitle())// 스토어 Name 수정
                .ifPresent(bookTitle -> findBook.setBookTitle(bookTitle));

        Optional.ofNullable(book.getBookSubTitle()) //스토어 AddressName 수정
                .ifPresent(bookSubTitle -> findBook.setBookSubTitle(bookSubTitle));


        Optional.ofNullable(book.getBookStatus())//book 삭제
                .ifPresent(bookStatus -> findBook.setBookStatus(bookStatus));

        return findBook;
    }

    public Page<Book> findBooks(int page, int size, String sort, long bookId) {

        List<Book> books = bookRepository.findByBookIdAndBookStatus(
                bookId, Book.BookStatus.BOOK_EXIST); //삭제된 스토어 뺴고 전체 스토어가져옴

        Comparator<Book> comparator;
        if (sort.equals("createdAt")) {//최신순 정렬
            comparator = new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getCreatedAt().isBefore(o2.getCreatedAt()) ? 1 : -1;
                }
            };
        }else { //sort의 쿼리스트링 파라미터가 올바른 값이 아님
            throw new BusinessLogicalException(ExceptionCode.SORT_NOT_FOUND);
        }
        Collections.sort(books, comparator);


        PageRequest pageRequest =PageRequest.of(page,size);
        int start = (int)pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), books.size());
        Page<Book> pagingStores = new PageImpl<>(books.subList(start, end), pageRequest, books.size());

        return pagingStores;

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