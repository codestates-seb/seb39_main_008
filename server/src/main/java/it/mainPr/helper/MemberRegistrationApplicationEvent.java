package it.mainPr.helper;

import it.mainPr.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberRegistrationApplicationEvent {
    private Member member;
    public MemberRegistrationApplicationEvent(Member member) {
        this.member = member;
    }
}