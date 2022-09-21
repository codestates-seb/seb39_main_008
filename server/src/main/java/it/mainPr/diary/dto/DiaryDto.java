package it.mainPr.diary.dto;

import it.mainPr.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class DiaryDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private Long diaryId;

        @NotBlank(message = "제목은 공백이 불가합니다")
        private String title;

        private String subtitle;

        private String img;
        @NotBlank(message = "내용은 공백이 불가합니다")
        private String content;

        private Member member;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch {
        private Long diaryId;

        @NotBlank(message = "제목은 공백이 불가합니다")
        private String title;

        private String subtitle;

        private String img;

        @NotBlank(message = "내용은 공백이 불가합니다")
        private String content;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long diaryId;
        private String title;
        private String subtitle;
        private String img;
        private Long memberId;
    }
}
