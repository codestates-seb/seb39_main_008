package it.mainPr.model;

import it.mainPr.audit.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "diary")
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
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "diary")
    private List<Comment> comments = new ArrayList<>();

//    @ManyToOne
//    private Book book;

    @Builder
    public Diary(String title, String subTitle, String nickname, String content, String diaryImgUrl, Member member) {
        this.title = title;
        this.subTitle = subTitle;
        this.nickname = nickname;
        this.content = content;
        this.DiaryImgUrl = diaryImgUrl;
        this.member = member;
    }

    public void update(String title, String subTitle, String content, String diaryImgUrl) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.DiaryImgUrl = diaryImgUrl;
    }

}