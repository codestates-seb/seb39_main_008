package it.mainPr.dto.bookDto;

import it.mainPr.model.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor
public class BookPostDto {
    @NotBlank
    private String bookTitle;
    @NotBlank
    private String bookSubTitle;

    private String bookImageUrl;
    private Member member;

    private List<BookImageDto> bookImages;

    @Builder
    public BookPostDto(String bookTitle, String bookSubTitle, String bookImageUrl) {
        this.bookTitle = bookTitle;
        this.bookSubTitle = bookSubTitle;
        this.bookImageUrl = bookImageUrl;
    }
}
