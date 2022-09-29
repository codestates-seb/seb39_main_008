package it.mainPr.mapper;

import it.mainPr.dto.MemberPatchDto;
import it.mainPr.dto.MemberPostDto;
import it.mainPr.dto.MemberResponseDto;
import it.mainPr.model.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostToMember(MemberPostDto post);
    MemberResponseDto memberToMemberResponse(Member member);
    Member memberPatchToMember(MemberPatchDto patch);
}