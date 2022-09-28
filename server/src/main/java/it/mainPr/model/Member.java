package it.mainPr.model;

import it.mainPr.audit.BaseTime;
import it.mainPr.dto.member.MemberPatchDto;
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

    @Column
    private String email;
    @Column
    private String password;

    @Column
    private String name;
    @Column
    private String nickname;
    @Column
    private String information;
    @Column
    private String imgUrl;


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public Member(String email) {
        this.email = email;
    }

    @Builder
    public Member(String email, String password, String name, String nickname, String information, String imgUrl) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.information = information;
        this.imgUrl = imgUrl;
        this.password = password;
    }

    public void update(MemberPatchDto memberPatchDto) {
        this.information = memberPatchDto.getInformation();
        this.imgUrl = memberPatchDto.getImgUrl();
    }

    public void update(String information, String imgUrl) {
        this.information = information;
        this.imgUrl = imgUrl;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}