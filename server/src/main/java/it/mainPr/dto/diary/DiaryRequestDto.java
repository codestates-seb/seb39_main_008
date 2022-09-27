package it.mainPr.dto.diary;

import it.mainPr.model.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryRequestDto {
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
