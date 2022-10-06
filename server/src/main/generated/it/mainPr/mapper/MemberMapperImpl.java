package it.mainPr.mapper;

import it.mainPr.dto.memberDto.MemberPatchDto;
import it.mainPr.dto.memberDto.MemberPostDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.dto.memberDto.MemberResponseDto.MemberResponseDtoBuilder;
import it.mainPr.model.Book;
import it.mainPr.model.Comment;
import it.mainPr.model.Diary;
import it.mainPr.model.Heart;
import it.mainPr.model.Member;
import it.mainPr.model.Member.MemberStatus;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-07T01:03:00+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostToMember(MemberPostDto post) {
        if ( post == null ) {
            return null;
        }

        String email = null;
        String password = null;
        String name = null;
        String nickname = null;

        email = post.getEmail();
        password = post.getPassword();
        name = post.getName();
        nickname = post.getNickname();

        Long memberId = null;
        String information = null;
        String role = null;
        MemberStatus memberStatus = null;
        String imgUrl = null;
        List<Book> book = null;
        List<Diary> diary = null;
        List<Heart> heart = null;
        List<Comment> comment = null;

        Member member = new Member( memberId, email, password, name, nickname, information, role, memberStatus, imgUrl, book, diary, heart, comment );

        return member;
    }

    @Override
    public MemberResponseDto memberToMemberResponseDto(Member member) {
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

        String information = null;
        String imgUrl = null;

        information = patch.getInformation();
        imgUrl = patch.getImgUrl();

        Long memberId = null;
        String email = null;
        String password = null;
        String name = null;
        String nickname = null;
        String role = null;
        MemberStatus memberStatus = null;
        List<Book> book = null;
        List<Diary> diary = null;
        List<Heart> heart = null;
        List<Comment> comment = null;

        Member member = new Member( memberId, email, password, name, nickname, information, role, memberStatus, imgUrl, book, diary, heart, comment );

        return member;
    }
}
