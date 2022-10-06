package it.mainPr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.mainPr.audit.BaseTime;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "diary", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<DiaryImage> diaryImgUrl;
    

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private DiaryStatus diaryStatus = DiaryStatus.DIARY_EXIST;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "diary", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "diary", fetch = FetchType.LAZY)
    private List<Heart> heart;

    @Builder
    public Diary(String diary_title, String diary_subtitle, Book book, String content, List<DiaryImage> diaryImgUrl, Member member) {
        this.diary_title = diary_title;
        this.diary_subtitle = diary_subtitle;
        this.content = content;
        this.diaryImgUrl = diaryImgUrl;
        this.member = member;
        this.book = book;
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


    public enum DiaryStatus {
        DIARY_EXIST("존재하는 일기장"),
        DIARY_NOT_EXIST("존재하지 않는 일기장");

        @Getter
        private String status;

        DiaryStatus(String status) {
            this.status = status;
        }
    }

}