package it.mainPr.mapper;

import it.mainPr.dto.bookDto.*;
import it.mainPr.dto.diaryDto.DiariesDto;
import it.mainPr.dto.global.MultiResponseDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.model.*;
import it.mainPr.service.*;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMapper {

    default Book bookPostDtoToBook(MemberService memberService, BookPostDto bookPostDto) {
        Book book = new Book();
        book.setBookTitle(bookPostDto.getBookTitle());
        book.setBookSubTitle(bookPostDto.getBookSubTitle());

//        if(bookPostDto.getHomepage()==null){
//            System.out.printf("홈페이지 아무것도 안들어옴");
//        }else{
//            book.setHomepage(bookPostDto.getHomepage());
//        }


        if (bookPostDto.getBookImages() == null) {
            System.out.printf("이미지 아무것도 안들어옴");
        } else {//이미지 한개 이상 들어왔을 때 -> 해당 이미지들을 Book객체에 넣어준다.
            List<BookImage> bookImages = bookImageDtosToBookImages(bookPostDto.getBookImages(), book);
            book.setBookImgUrl(bookImages);
        }

        Member member = memberService.getLoginMember();// 현재 로그인된 유저 가져옴
        book.setMember(member);

        return book;
    }

    default List<BookImage> bookImageDtosToBookImages(List<BookImageDto> bookImageDtos, Book book) {
        // changing from bookImageDtos to BookImages
        return bookImageDtos.stream().map(bookImageDto -> {
            BookImage bookImage = new BookImage();
            bookImage.setBook(book);
            bookImage.setBookImgUrl(bookImageDto.getBookImgUrl());
            return bookImage;
        }).collect(Collectors.toList());
    }

    default List<BookImageResponseDto> bookImagesToBookImageResponseDtos(List<BookImage> bookImages) {

        return bookImages.stream()
                .map(bookImage -> {
                    BookImageResponseDto bookImageResponseDto = new BookImageResponseDto();
                    bookImageResponseDto.setBookImgUrl(bookImage.getBookImgUrl());
                    bookImageResponseDto.setBookImageStatus(bookImage.getBookImageStatus());
                    return bookImageResponseDto;
                }).collect(Collectors.toList());
    }

    default BookAndDiaryResponseDto bookToBookAndDiaryResponse(DiaryService diaryService, HeartService heartService, DiaryMapper diaryMapper, MemberMapper memberMapper, BookImageService bookImageService, Book book) {

        BookAndDiaryResponseDto bookAndDiaryResponseDto = new BookAndDiaryResponseDto();
        bookAndDiaryResponseDto.setBookId(book.getBookId());
        bookAndDiaryResponseDto.setBookStatus(book.getBookStatus());
        bookAndDiaryResponseDto.setBookTitle(book.getBookTitle());
        bookAndDiaryResponseDto.setBookSubTitle(book.getBookSubTitle());

        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(book.getMember());
        bookAndDiaryResponseDto.setMember(memberResponseDto);

        bookAndDiaryResponseDto.setBookImgUrls(bookImagesToBookImageResponseDtos( //북에 대한 이미지 속성 추가
                bookImageService.findVerifiedBookImages(book)
        ));

        return bookAndDiaryResponseDto;
    }

    default BookAndDiaryResponseDto bookToBookAndDiaryResponseDto(DiaryService diaryService, HeartService heartService, DiaryMapper diaryMapper, MemberMapper memberMapper, BookImageService bookImageService,
                                                                  Book book, Integer diaryPage, Integer diarySize, String diarySort){

        BookAndDiaryResponseDto bookAndDiaryResponseDto = new BookAndDiaryResponseDto();
        bookAndDiaryResponseDto.setBookId(book.getBookId());
        bookAndDiaryResponseDto.setBookStatus(book.getBookStatus());
        bookAndDiaryResponseDto.setBookTitle(book.getBookTitle());
        bookAndDiaryResponseDto.setBookSubTitle(book.getBookSubTitle());

        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(book.getMember());
        bookAndDiaryResponseDto.setMember(memberResponseDto);

        bookAndDiaryResponseDto.setBookImgUrls(bookImagesToBookImageResponseDtos( //북에 대한 이미지 속성 추가
                bookImageService.findVerifiedBookImages(book)
        ));

        Page<Diary> pageDiaries = diaryService.findExistDiariesToPaginationAndSort(
                book,diaryPage,diarySize,diarySort);// book의 diarys중 status가 true인 것만 페이지네이션 정렬해서 반환
        List<Diary> diaries = pageDiaries.getContent();
        System.out.println(pageDiaries.getContent());
        bookAndDiaryResponseDto.setDiaries(new MultiResponseDto<>(
                diaryMapper.diariesToDiaryResponse(memberMapper,diaries),pageDiaries
        ));


//        List<Heart> hearts = heartService.findExistHeartByBook(book);//해당 book의 하트중에 Status가 HEART_EXIST인 하트들만 반환
//        List<Long> heartMemberId = hearts.stream().map(heart -> heart.getMember().getMemberId()).collect(Collectors.toList());
//        bookAndDiaryResponseDto.setHeartMemberId(heartMemberId);

        return bookAndDiaryResponseDto;
    }

    default BookResponseDto bookToBookResponseDto(DiaryService diaryService, DiaryMapper diaryMapper, MemberMapper memberMapper, BookImageService bookImageService, Book book){
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setBookId(book.getBookId());
        bookResponseDto.setBookStatus(book.getBookStatus());
        bookResponseDto.setBookTitle(book.getBookTitle());
        bookResponseDto.setBookSubTitle(book.getBookSubTitle());

        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(book.getMember());
        bookResponseDto.setMember(memberResponseDto);

        bookResponseDto.setBookImgUrl(bookImagesToBookImageResponseDtos(
                bookImageService.findVerifiedBookImages(book)
        ));

        List<DiariesDto.DiaryResponseDto> diaryResponseDtos = diaryMapper.diariesToExistDiaryResponseDtos(diaryService,memberMapper,book.getDiary());
        bookResponseDto.setDiaries(diaryResponseDtos);


        return bookResponseDto;
    }

    default List<BookResponseDto> booksToBookResponseDtos(DiaryService diaryService,HeartService heartService,DiaryMapper diaryMapper, MemberMapper memberMapper, BookImageService bookImageService, List<Book> books){
        return books.stream().map(book -> bookToBookResponseDto(diaryService, diaryMapper, memberMapper, bookImageService, book))
                .collect(Collectors.toList());
    };

    default Book bookPatchDtoToBook(BookService bookService, MemberService memberService, long bookId, BookPatchDto bookPatchDto){

        if(memberService.getLoginMember() != bookService.findMemberAtBook(bookId)){
            throw new BusinessLogicalException(ExceptionCode.ACCESS_DENIED_MEMBER);
        }
        Book book = new Book();
        book.setBookId(bookId);

        book.setMember(memberService.getLoginMember());

        // changing from BookImageDto to BookImage
        if(bookPatchDto.getBookImgUrls() == null) {
            System.out.printf("이미지 아무것도 안들어왔습니다.");
        }else{//이미지 한개 이상 들어왔을 때 -> 해당 이미지들을 Book객체에 넣어준다.
            List<BookImage> bookImages = bookImageDtosToBookImages(bookPatchDto.getBookImgUrls(),book);
            book.setBookImgUrl(bookImages);
        }


        book.setBookTitle(bookPatchDto.getBookTitle());
        book.setBookSubTitle(bookPatchDto.getBookSubTitle());

        //스토어 삭제
        book.setBookStatus(bookPatchDto.getBookStatus());
        System.out.println(book.getBookStatus());

        return book;

    }
}