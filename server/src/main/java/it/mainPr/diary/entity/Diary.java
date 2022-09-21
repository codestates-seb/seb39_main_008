package it.mainPr.diary.entity;


import it.mainPr.audit.Auditable;
import it.mainPr.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DIARY")
public class Diary extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String subTitle;

    private String nickname;

    /* CLOB : 문자 대형 객체,
    BLOB: 이진 대형 객체(이미지, 동영상 등)
     */
    @Lob
    private String content;

    @Lob
    private String img;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

//    @OneToMany(mappedBy = "diary", fetch = FetchType.EAGER)
//    @JsonIgnoreProperties("diary")
//    @OrderBy("id desc")
//    private List<Comment> comment;


}
