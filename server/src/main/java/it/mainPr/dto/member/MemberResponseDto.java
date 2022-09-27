package it.mainPr.dto.member;

import it.mainPr.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getMemberId(), member.getEmail(), member.getName(),
                member.getNickname(), member.getInformation(), member.getImgUrl());
    }
}
