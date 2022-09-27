package it.mainPr.dto.diary;

import it.mainPr.model.Diary;
import lombok.Getter;

@Getter
public class DiaryResponseDto {
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
