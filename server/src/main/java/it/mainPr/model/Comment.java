package it.mainPr.model;

import it.mainPr.audit.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Setter
@NoArgsConstructor
@Table(name = "COMMENT")
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private CommentStatus commentStatus = CommentStatus.COMMENT_EXIST;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @Builder
    public Comment(String content, Member member, Diary diary) {
        this.content = content;
        this.member = member;
        this.diary = diary;
    }

    public enum CommentStatus {
        COMMENT_EXIST("존재하는 댓글"),
        COMMENT_NOT_EXIST("존재하지 않는 댓글");

        @Getter
        private String status;

        CommentStatus(String status) {
            this.status = status;
        }
    }
}