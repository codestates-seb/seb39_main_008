package it.mainPr.model;

import it.mainPr.audit.BaseTime;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "HEART")
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private HeartStatus heartStatus = HeartStatus.HEART_EXIST;


    public enum HeartStatus {
        HEART_EXIST("존재하는 하트"),
        HEART_NOT_EXIST("존재하지 않는 하트");

        @Getter
        private String status;

        HeartStatus(String status) {
            this.status = status;
        }
    }
}
