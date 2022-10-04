package it.mainPr.dto.heartDto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class HeartPostDto {
    @NotNull(message = "DiaryId를 입력해주세요")
    private Long diaryId;
}
