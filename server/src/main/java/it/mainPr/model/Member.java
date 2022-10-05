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
    @JsonIgnore
    private String password;
    private String name;
    private String nickname;
    private String information;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private MemberStatus memberStatus = MemberStatus.MEMBER_EXIST;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String imgUrl;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Book> book;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Diary> diary;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Heart> heart;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comment;


    @Builder
    public Member(long memberId, String email, String password, String name, String nickname, String information, String imgUrl) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.information = information;
        this.imgUrl = imgUrl;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();


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