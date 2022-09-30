package it.mainPr.model;

import it.mainPr.audit.BaseTime;
import it.mainPr.dto.MemberPatchDto;
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
    private String imgUrl;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Diary> diary = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Heart> heart;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comment;


    @Builder
    public Member(Long memberId, String email, String password, String name, String nickname, String information, String imgUrl) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.information = information;
        this.imgUrl = imgUrl;
   //     this.role = role  == null ? Role.ROLE_MEMBER : role;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public Member(String email) {
        this.email = email;
    }


}