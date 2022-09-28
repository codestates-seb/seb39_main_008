//package it.mainPr.model;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Getter
//@Entity
//@NoArgsConstructor
//public class Like {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "like_id")
//    private Long likeId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Long memberId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "diary_id")
//    private Long diaryId;
//
//    @Builder
//    public Like(Long memberId, Long diaryId) {
//        this.memberId = memberId;
//        this.diaryId = diaryId;
//    }
//}
