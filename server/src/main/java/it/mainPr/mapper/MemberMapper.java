package it.mainPr.mapper;

import it.mainPr.dto.member.MemberPatchDto;
import it.mainPr.dto.member.MemberPostDto;
import it.mainPr.dto.member.MemberResponseDto;
import it.mainPr.model.member.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostToMember(MemberPostDto post);
    MemberResponseDto memberToMemberResponse(Member member);
    Member memberPatchToMember(MemberPatchDto patch);
}
