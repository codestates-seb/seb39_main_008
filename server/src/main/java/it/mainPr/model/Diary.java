package it.mainPr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private String diary_title;

    private String diary_subtitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private String DiaryImgUrl;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;

    @OneToMany(mappedBy = "diary", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(mappedBy = "diary", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Heart> heart;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Builder
    public Diary(String diary_title, String diary_subtitle, String nickname, String content, String diaryImgUrl, Member member) {
        this.diary_title = diary_title;
        this.diary_subtitle = diary_subtitle;
        this.content = content;
        this.DiaryImgUrl = diaryImgUrl;
        this.member = member;
    }



    //비즈니스 로직
    public void setMember(Member member) {
        this.member = member;
    }

    public void updateDiaries(String diary_title, String content, String diary_subtitle){
        this.diary_title = diary_title;
        this.content = content;
        this.diary_subtitle = diary_subtitle;
    }

    public void updateDiaries(String content) {
        this.content = content;
    }

    public void updateTitle(String diary_title) {
        this.diary_title = diary_title;
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