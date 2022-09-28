package it.mainPr.dto;

import it.mainPr.model.Diary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

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
    }

    @Getter
    @NoArgsConstructor
    public static class PatchDto {
        private Long diaryId;
        private String title;
        private String subtitle;
        private String content;
        private String diaryImgUrl;

        public void setDiaryId(Long diaryId) {
            this.diaryId = diaryId;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class DiaryRequestDto {
        private String title;
        private String subtitle;
        private String content;
        private String nickname;
        private String diaryImgUrl;

        @Builder
        public DiaryRequestDto(String title, String subtitle, String content, String nickname, String diaryImgUrl) {
            this.title = title;
            this.subtitle = subtitle;
            this.content = content;
            this.nickname = nickname;
            this.diaryImgUrl = diaryImgUrl;
        }

        public Diary toEntity() {
            return Diary.builder()
                    .title(title)
                    .subTitle(subtitle)
                    .content(content)
                    .nickname(nickname)
                    .diaryImgUrl(diaryImgUrl)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiaryResponseDto {
        private Long diaryId;
        private String title;
        private String subtitle;
        private String nickname;
        private String diaryImgUrl;
        private String content;
        private Long memberId;

        public DiaryResponseDto(Diary entity) {
            this.diaryId = entity.getDiaryId();
            this.title = entity.getTitle();
            this.subtitle = entity.getSubTitle();
            this.content = entity.getContent();
            this.diaryImgUrl = entity.getDiaryImgUrl();
            this.memberId = entity.getMember().getMemberId();
            this.nickname = entity.getNickname();
        }

    }
}
