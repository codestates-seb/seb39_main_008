package it.mainPr.dto;

import it.mainPr.model.Diary;
import it.mainPr.model.Member;
import lombok.*;
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
        String title;
        String subtitle;
        @NotBlank
        String content;
        String diaryImgUrl;
        Member member;
        Integer likeCount;
        Integer followCount;

        @Builder
        public PostDto(String title, String subtitle, String content, String diaryImgUrl, Member member, Integer likeCount, Integer followCount) {
            this.title = title;
            this.subtitle = subtitle;
            this.content = content;
            this.diaryImgUrl = diaryImgUrl;
            this.member = member;
            this.likeCount = likeCount;
            this.followCount = followCount;
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
        private long memberId;
        private String diaryImgUrl;
        private String total_hearts;
        private String total_comments;
        private List<CommentsDto.ResponseDto> comments;
        private List<HeartResponseDto> hearts;
        private Diary.Category category;

        @Builder
        public DiaryResponseDto(Long diaryId, String title, String subtitle, String content, String nickname, long memberId, String diaryImgUrl, String total_hearts, String total_comments, List<CommentsDto.ResponseDto> comments, List<HeartResponseDto> hearts, Diary.Category category) {
            this.diaryId = diaryId;
            this.title = title;
            this.subtitle = subtitle;
            this.content = content;
            this.nickname = nickname;
            this.memberId = memberId;
            this.diaryImgUrl = diaryImgUrl;
            this.total_hearts = total_hearts;
            this.total_comments = total_comments;
            this.comments = comments;
            this.hearts = hearts;
            this.category = category;
        }
    }
    }