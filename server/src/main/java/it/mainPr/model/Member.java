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
    private String imgUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Book> book = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Diary> diary = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Heart> heart;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> comment;

    @ManyToOne
    @JoinColumn
    private Member memberFollowing = this;

    @ManyToOne
    @JoinColumn
    private Member memberFollower = this;

    @OneToMany(mappedBy = "memberFollowing")
    private List<Member> followingList = new ArrayList<>();

    @OneToMany(mappedBy = "memberFollower")
    private List<Member> followerList = new ArrayList<>();

    public void addFollowing(Member following) {
        this.followingList.add(following);

        if(! following.getFollowerList().contains(this)) {
            following.getFollowerList().add(this);
        }
        if(! following.getMemberFollower().getFollowerList().contains(this)) {
            following.getMemberFollower().getFollowerList().add(this);
        }
    }

    public void addFollower(Member follower) {
        this.followerList.add(follower);

        if(! follower.getFollowingList().contains(this)) {
            follower.getFollowingList().add(this);
        }

        if(! follower.getMemberFollowing().getFollowingList().contains(this)) {
            follower.getMemberFollower().getFollowingList().add(this);
        }
    }

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


}