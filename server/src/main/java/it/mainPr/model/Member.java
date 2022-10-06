package it.mainPr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.mainPr.audit.BaseTime;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "MEMBER")
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String information;

    private String Role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private MemberStatus memberStatus = MemberStatus.MEMBER_EXIST;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String imgUrl;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Book> book;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Diary> diary;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Heart> heart;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comment;


    public enum MemberStatus {
        MEMBER_EXIST("존재하는 유저"),
        MEMBER_NOT_EXIST("존재하지 않는 유저");

        @Getter
        private String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }
}