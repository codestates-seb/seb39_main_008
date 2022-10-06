package it.mainPr.dto.bookDto;

import it.mainPr.model.Book;
import lombok.Getter;

import java.util.List;

@Getter
public class BookPatchDto {
    private String bookTitle;
    private String bookSubTitle;
    private List<BookImageDto> bookImgUrls;
    private Book.BookStatus bookStatus;
}
