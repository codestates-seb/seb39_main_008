package it.mainPr.dto.bookDto;

import it.mainPr.model.BookImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookImageResponseDto {

    private BookImage.BookImageStatus bookImageStatus;
    private String bookImgUrl;
}
