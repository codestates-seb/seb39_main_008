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
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder
    public Member(Long memberId, String email, String password, String name, String nickname, String information, String imgUrl, Role role) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.information = information;
        this.imgUrl = imgUrl;
        this.role = role  == null ? Role.ROLE_MEMBER : role;
    }

    //    @ElementCollection(fetch = FetchType.EAGER)
//    private List<String> roles = new ArrayList<>();
    @Builder


    public Member(String email) {
        this.email = email;
    }

    @Getter
    public enum Role {

        ROLE_ADMIN("관리자"), ROLE_MEMBER("일반회원");

        private String description;

        Role(String description) {
            this.description = description;
        }
    }
}