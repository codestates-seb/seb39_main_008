package it.mainPr.dto;

import it.mainPr.model.Diary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Validated
public class DiariesDto {

    @Getter
    @NoArgsConstructor
    public static class PostDto {
        @NotBlank
        private String title;
        private String subtitle;
        @NotBlank
        private String content;
        private String diaryImgUrl;
//        private Integer likeCount;
//        private Integer followCount;

        @Builder
        public PostDto(String title, String subtitle, String content, String diaryImgUrl) {
            this.title = title;
            this.subtitle = subtitle;
            this.content = content;
            this.diaryImgUrl = diaryImgUrl;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class PatchDto {
        private Long diaryId;
        private String title;
        private String subtitle;
        private String content;
        private String diaryImgUrl;

        public void setDiaryId(long diaryId) {
            this.diaryId = diaryId;
        }

        @Builder
        public PatchDto(Long diaryId, String title, String subtitle, String content, String diaryImgUrl) {
            this.diaryId = diaryId;
            this.title = title;
            this.subtitle = subtitle;
            this.content = content;
            this.diaryImgUrl = diaryImgUrl;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class DiaryResponseDto {
        private Long diaryId;
        private String title;
        private String subtitle;
        private String content;
        private String nickname;
        private Long memberId;
        private String diaryImgUrl;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private List<CommentsDto.ResponseDto> comments;

        @Builder
        public DiaryResponseDto(Long diaryId, String title, String subtitle, String content, String nickname, Long memberId, String diaryImgUrl, LocalDateTime createdAt, LocalDateTime modifiedAt, List<CommentsDto.ResponseDto> comments) {
            this.diaryId = diaryId;
            this.title = title;
            this.subtitle = subtitle;
            this.content = content;
            this.nickname = nickname;
            this.memberId = memberId;
            this.diaryImgUrl = diaryImgUrl;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
            this.comments = comments;
        }
    }
}