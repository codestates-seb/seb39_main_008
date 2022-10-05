package it.mainPr.dto.diaryDto;

import it.mainPr.audit.BaseTime;
import it.mainPr.dto.bookDto.BookResponseDto;
import it.mainPr.dto.commentDto.CommentResponseDto;
import it.mainPr.dto.heartDto.HeartResponseDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.model.Diary;
import it.mainPr.model.Member;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
public class DiariesDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PostDto {
        @NotBlank
        private String diary_title;
        private String diary_subtitle;
        @NotBlank
        private String content;
        private String diaryImgUrl;

        @NotBlank
        @Pattern(regexp = "(^일상 공유$)|(^공감과 치유$)|(^문화 생활$)" +
                "|(^여행기록$)|(^자유$)",message = "카테고리 중에 선택해주세요")
        private String category;
        private Member member;
        private Integer total_heart;
        private Integer total_follow;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PatchDto {
        private Long diaryId;
        private String diary_title;
        private String diary_subtitle;
        private String content;
        @Pattern(regexp = "(^일상 공유$)|(^공감과 치유$)|(^문화 생활$)" +
                "|(^여행기록$)|(^자유$)",message = "카테고리 중에 선택해주세요")
        private String category;
        private Diary.DiaryStatus diaryStatus;
        private String information;
        private String diaryImgUrl;

        public void setDiaryId(long diaryId) {
            this.diaryId = diaryId;
        }

    }

    @Getter
    @Setter
    public static class DiaryResponseDto {
        private Long diaryId;
        private String diary_title;
        private String diary_subtitle;
        private String content;
        private String diaryImgUrl;
        private Diary.DiaryStatus diaryStatus;
        private String total_hearts;
        private String total_comments;
        private MemberResponseDto member;
        private List<CommentResponseDto> comments;
        private List<HeartResponseDto> hearts;
        private String category;
        private List<BookResponseDto> books;
        private List<Long> heartMemberId;

    }

}