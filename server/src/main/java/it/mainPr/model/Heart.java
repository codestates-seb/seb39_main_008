package it.mainPr.model;

import it.mainPr.audit.BaseTime;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "HEART")
@Builder
public class Heart extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long heartId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "diary_id")
    private Diary diary;

    public Heart(Member member, Diary diary) {
        this.member = member;
        this.diary = diary;
    }
}
