package it.mainPr.mapper;

import it.mainPr.dto.MemberPatchDto;
import it.mainPr.dto.MemberPostDto;
import it.mainPr.dto.MemberResponseDto;
import it.mainPr.model.Member;
import it.mainPr.model.Member.MemberBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-29T22:41:16+0900",
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

        MemberResponseDto memberResponseDto = new MemberResponseDto();

        memberResponseDto.setMemberId( member.getMemberId() );
        memberResponseDto.setEmail( member.getEmail() );
        memberResponseDto.setName( member.getName() );
        memberResponseDto.setNickname( member.getNickname() );
        memberResponseDto.setInformation( member.getInformation() );
        memberResponseDto.setImgUrl( member.getImgUrl() );

        return memberResponseDto;
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
