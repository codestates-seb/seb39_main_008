package it.mainPr.dto;

import it.mainPr.model.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String email;
    private String name;
    private String nickname;
    private String information;
    private String imgUrl;

//    @Builder
//    public MemberResponseDto(Long memberId, String email, String name, String nickname, String information, String imgUrl) {
//        this.memberId = memberId;
//        this.email = email;
//        this.name = name;
//        this.nickname = nickname;
//        this.information = information;
//        this.imgUrl = imgUrl;
//    }

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getMemberId(), member.getEmail(), member.getName(),
                member.getNickname(), member.getInformation(), member.getImgUrl());
    }
}
