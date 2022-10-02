package it.mainPr.dto.commentDto;

import it.mainPr.dto.memberDto.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Validated
public class CommentsDto {

    @Getter
    @NoArgsConstructor
    public static class PostDto {
        private Long diaryId;
        private String content;
        private LocalDateTime createdAt = LocalDateTime.now();
    }

    @Getter
    @NoArgsConstructor
    public static class PatchDto {
        private String content;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResponseDto {
        private Long commentId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private MemberResponseDto member;
    }
}
