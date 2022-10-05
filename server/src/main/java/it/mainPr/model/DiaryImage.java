package it.mainPr.model;

import it.mainPr.audit.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DIARY_IMAGE")
public class DiaryImage extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryImageId;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String image;

    @ManyToOne
    @JoinColumn(name = "diary_id")
    private Diary diary;

    public void addDiary(Diary diary) {
        this.diary = diary;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private DiaryImageStatus diaryImageStatus = DiaryImageStatus.DIARY_IMAGE_EXIST;


    public enum DiaryImageStatus {
        DIARY_IMAGE_EXIST("다이어리 사진이 존재합니다"),
        DIARY_IAMGE_NOT_EXIST("다이어리 사진이 존재하지 않습니다");

        @Getter
        private String status;

        DiaryImageStatus(String status) {
            this.status = status;
        }
    }
}
