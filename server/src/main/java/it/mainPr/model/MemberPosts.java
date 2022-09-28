//package it.mainPr.model;
//
//import it.mainPr.model.member.Member;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Getter
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class MemberPosts {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long memberPostId;
//
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;
//
//    @ManyToOne
//    @JoinColumn(name = "diary_id")
//    private Diary diary;
//
//    @Builder
//    public MemberPosts(Long memberPostId, Member member, Diary diary) {
//        this.memberPostId = memberPostId;
//        this.member = member;
//        this.diary = diary;
//    }
//
//    }
//}
