package it.mainPr.dto.commentDto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
public class CommentPostDto {
    @Positive
    private Long commentId;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;
}
