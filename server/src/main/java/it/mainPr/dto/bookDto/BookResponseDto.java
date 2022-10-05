package it.mainPr.dto.bookDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.mainPr.dto.heartDto.HeartResponseDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.model.Book;
import it.mainPr.model.Diary;
import it.mainPr.model.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookResponseDto {
    private Long bookId;
    @NotBlank
    private String bookTitle;
    @NotBlank
    private String bookSubTitle;
    private String bookImageUrl;
    @JsonIgnore
    private Member member;
    @JsonIgnore
    private List<Diary> diary;

    @Builder
    public BookResponseDto(Long bookId, String bookTitle, String bookSubTitle, String bookImageUrl, Member member, List<Diary> diary) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookSubTitle = bookSubTitle;
        this.bookImageUrl = bookImageUrl;
        this.member = member;
        this.diary = diary;
    }

    public static BookResponseDto of(Book book) {
        return new BookResponseDto(book.getBookId(), book.getBookTitle(), book.getBookSubTitle(), book.getBookImageUrl(),
                book.getMember(), book.getDiary());
    }
}
