package it.mainPr.diary.dto;

import it.mainPr.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaryPostDto {

    private Long id;

    @NotBlank(message = "제목은 공백이 불가합니다")
    private String title;

    private String subtitle;

    private String img;

    @NotBlank(message = "내용은 공백이 불가합니다")
    private String content;

    private Member member;
}
