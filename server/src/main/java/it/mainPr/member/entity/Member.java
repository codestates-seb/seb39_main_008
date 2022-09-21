package it.mainPr.member.entity;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "MEMBERS")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String img;

    @Column(nullable = false)
    private String introduction;

    @Column(unique = true)
    private String email;
    private String name;
    private String nickname;

    @JsonIgnore
    private String password;

    @Builder
    public Member(String img, String introduction, String email, String name, String nickname, String password) {
        this.img = img;
        this.introduction = introduction;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
    }
}
