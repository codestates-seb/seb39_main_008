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

    @Column(nullable = false)
    private String diary_title;
    @Column(nullable = false)
    private String diary_subtitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "diary", fetch = FetchType.LAZY)
    private List<DiaryImage> diaryImgUrl;

    @Column(nullable = false)
    private String category;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private DiaryStatus diaryStatus = DiaryStatus.DIARY_EXIST;


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

//    @Getter
//    @AllArgsConstructor
//    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
//    public enum Category {
//        CATEGORY_0("일상공유"), CATEGORY_1("공감과치유");
//
//        private String description;
//    }

    public enum DiaryStatus {
        DIARY_EXIST("존재하는 장소"),
        DIARY_NOT_EXIST("존재하지 않는 장소");

        @Getter
        private String status;

        DiaryStatus(String status) {
            this.status = status;
        }
    }

}