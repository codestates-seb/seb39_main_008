package it.mainPr.model;

import it.mainPr.audit.BaseTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    public Comment(String content, Member member, Diary diary) {
        this.content = content;
        this.member = member;
        this.diary = diary;
    }

    public static Comment createComment(String content, Member member, Diary diary) {
        Comment comment = new Comment(content,member,diary);
        diary.getComments().add(comment);
        return comment;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
