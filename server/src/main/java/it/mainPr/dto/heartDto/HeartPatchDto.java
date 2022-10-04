package it.mainPr.dto.heartDto;

import it.mainPr.model.Heart;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class HeartPatchDto {

    //취소
    @NotNull(message = "취소를 원하는 게시글 DiaryId를 입력하세요")
    private Long diaryId;

    @NotNull(message = "HEART_NOT_EXIST를 입력해주세요")
    private Heart.HeartStatus heartStatus;
}
