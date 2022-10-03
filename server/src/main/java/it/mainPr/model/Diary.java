package it.mainPr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.mainPr.audit.BaseTime;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Diary")
public class Diary extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long diaryId;

    private String title;

    private String subTitle;

    private String nickname;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private String DiaryImgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

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

    public void addMember(Member member) {
        this.member = member;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Getter
    @AllArgsConstructor
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum Category {
        CATEGORY_0("일상공유"), CATEGORY_1("공감과치유");

        private String description;
    }
}