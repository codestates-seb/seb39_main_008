package it.mainPr.follow.entity;

import it.mainPr.audit.Auditable;
import it.mainPr.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FOLLOW")
public class Follow extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "FOLLOW_STATUS")
    private FollowStatus followStatus = FollowStatus.FOLLOW_EXIST;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

//    @ManyToOne
//    @JoinColumn(name = "DIARY_ID")
//    private Diary diary;


    public enum FollowStatus {
        FOLLOW_EXIST("팔로우 중"),
        FOLLOW_NOT_EXIST("팔로우 하지않는중");

        @Getter
        private final String followStatus;

        FollowStatus(String followStatus) {
            this.followStatus = followStatus;
        }

    }
}
