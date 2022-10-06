package it.mainPr.dto.bookDto;

import it.mainPr.dto.diaryDto.DiariesDto;
import it.mainPr.dto.global.MultiResponseDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.model.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookAndDiaryResponseDto {
    private Long bookId;
    private Book.BookStatus bookStatus;
    private String bookTitle;
    private String bookSubTitle;
    private MemberResponseDto member;
    private List<BookImageResponseDto> bookImgUrls;

    //다이어리 추가
    private MultiResponseDto<DiariesDto.DiaryResponseDto> diaries;

}
