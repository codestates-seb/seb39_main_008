package it.mainPr.dto.diaryDto;

import it.mainPr.model.DiaryImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryImageResponseDto {
    private Long diaryImageId;
    private DiaryImage.DiaryImageStatus diaryImageStatus;
    private String diaryImgUrl;
}
