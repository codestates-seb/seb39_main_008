package it.mainPr.dto.bookDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.mainPr.dto.diaryDto.DiariesDto;
import it.mainPr.dto.heartDto.HeartResponseDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.model.Book;
import it.mainPr.model.BookImage;
import it.mainPr.model.Diary;
import it.mainPr.model.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class BookResponseDto {
    private Long bookId;
    private String bookTitle;
    private String bookSubTitle;
    private List<BookImageResponseDto> bookImgUrl;
    private MemberResponseDto member;
    private List<Diary> diary;
    private Book.BookStatus bookStatus;

    private List<DiariesDto.DiaryResponseDto> diaries;
    private List<Long> heartMemberId;

    }
