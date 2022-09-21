package it.mainPr.like.entity;

import it.mainPr.audit.Auditable;
import it.mainPr.diary.entity.Diary;
import it.mainPr.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LIKE")
public class Like extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "LIKE_STATUS")
    private LikeStatus likeStatus = LikeStatus.LIKE_EXIST;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "DIARY_ID")
    private Diary diary;

    public enum LikeStatus {
        LIKE_EXIST("좋아요"),
        LIKE_NOT_EXIST("빈 좋아요");

        @Getter
        private final String likeStatus;

        LikeStatus(String likeStatus) {
            this.likeStatus = likeStatus;
        }
    }
}
