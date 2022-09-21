package it.mainPr.diary.dto;

import it.mainPr.diary.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryResponseDto {

        private Long diaryId;
        private String title;
        private String subtitle;
        private String nickname;
        private String img;
        private String content;
        private Long memberId;

        public DiaryResponseDto(Diary diary) {
            this.diaryId = diary.getDiaryId();
            this.title = diary.getTitle();
            this.subtitle = diary.getSubTitle();
            this.content = diary.getContent();
            this.img = diary.getImg();
            this.memberId = diary.getMember().getMemberId();
            this.nickname = diary.getNickname();
        }


}
