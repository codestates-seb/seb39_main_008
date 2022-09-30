package it.mainPr.dto;

import it.mainPr.model.Diary;
import it.mainPr.model.Member;
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
        private Member member;
//        private Integer likeCount;
//        private Integer followCount;

        @Builder
        public PostDto(String title, String subtitle, String content, String diaryImgUrl, Member member) {
            this.title = title;
            this.subtitle = subtitle;
            this.content = content;
            this.diaryImgUrl = diaryImgUrl;
            this.member = member;
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
        private Member member;
        private String diaryImgUrl;
        private List<CommentsDto.ResponseDto> comments;
        private List<HeartResponseDto> hearts;

        @Builder
        public DiaryResponseDto(Long diaryId, String title, String subtitle, String content, String nickname, Member member, String diaryImgUrl, List<CommentsDto.ResponseDto> comments, List<HeartResponseDto> hearts) {
            this.diaryId = diaryId;
            this.title = title;
            this.subtitle = subtitle;
            this.content = content;
            this.nickname = nickname;
            this.member = member;
            this.diaryImgUrl = diaryImgUrl;
            this.comments = comments;
            this.hearts = hearts;
        }
    }
}