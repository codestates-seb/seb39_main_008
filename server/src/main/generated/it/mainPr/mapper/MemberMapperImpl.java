package it.mainPr.mapper;

import it.mainPr.dto.memberDto.MemberPatchDto;
import it.mainPr.dto.memberDto.MemberPostDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.dto.memberDto.MemberResponseDto.MemberResponseDtoBuilder;
import it.mainPr.model.Member;
import it.mainPr.model.Member.MemberBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-04T01:17:38+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostToMember(MemberPostDto post) {
        if ( post == null ) {
            return null;
        }

        MemberBuilder member = Member.builder();

        member.email( post.getEmail() );
        member.password( post.getPassword() );
        member.name( post.getName() );
        member.nickname( post.getNickname() );

        return member.build();
    }

    @Override
    public MemberResponseDto memberToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponseDtoBuilder memberResponseDto = MemberResponseDto.builder();

        memberResponseDto.memberId( member.getMemberId() );
        memberResponseDto.email( member.getEmail() );
        memberResponseDto.name( member.getName() );
        memberResponseDto.nickname( member.getNickname() );
        memberResponseDto.information( member.getInformation() );
        memberResponseDto.imgUrl( member.getImgUrl() );

        return memberResponseDto.build();
    }

    @Override
    public Member memberPatchToMember(MemberPatchDto patch) {
        if ( patch == null ) {
            return null;
        }

        MemberBuilder member = Member.builder();

        member.information( patch.getInformation() );
        member.imgUrl( patch.getImgUrl() );

        return member.build();
    }
}
