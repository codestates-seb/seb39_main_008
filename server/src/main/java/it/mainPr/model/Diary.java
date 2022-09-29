package it.mainPr.model;

import it.mainPr.audit.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "DIARY")
public class Diary extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long diaryId;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String subTitle;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String DiaryImgUrl;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "diary", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "diary", fetch = FetchType.LAZY)
    private List<Heart> heart;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Builder
    public Diary(String title, String subTitle, String nickname, String content, String diaryImgUrl, Member member) {
        this.title = title;
        this.subTitle = subTitle;
        this.nickname = nickname;
        this.content = content;
        this.DiaryImgUrl = diaryImgUrl;
        this.member = member;
    }



    //비즈니스 로직
    public void setMember(Member member) {
        this.member = member;
    }

    public void updateDiaries(String title, String content, String subTitle){
        this.title = title;
        this.content = content;
        this.subTitle = subTitle;
    }

    public void updateDiaries(String content) {
        this.content = content;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateDiaryImages() {
    }

    @Getter
    public enum Category {
        CATEGORY_0("일상공유"), CATEGORY_1("공감과치유");

        private String description;

        Category(String description) {
            this.description = description;
        }
    }
}