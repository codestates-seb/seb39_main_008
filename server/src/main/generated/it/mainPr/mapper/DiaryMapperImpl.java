package it.mainPr.mapper;

import it.mainPr.dto.commentDto.CommentsDto.ResponseDto;
import it.mainPr.dto.commentDto.CommentsDto.ResponseDto.ResponseDtoBuilder;
import it.mainPr.dto.diaryDto.DiariesDto.DiaryResponseDto;
import it.mainPr.dto.diaryDto.DiariesDto.DiaryResponseDto.DiaryResponseDtoBuilder;
import it.mainPr.dto.diaryDto.DiariesDto.PostDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.dto.memberDto.MemberResponseDto.MemberResponseDtoBuilder;
import it.mainPr.model.Comment;
import it.mainPr.model.Diary;
import it.mainPr.model.Diary.DiaryBuilder;
import it.mainPr.model.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-04T15:42:18+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class DiaryMapperImpl implements DiaryMapper {

    @Override
    public Diary postDtoToDiary(PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        DiaryBuilder diary = Diary.builder();

        diary.diary_title( postDto.getDiary_title() );
        diary.diary_subtitle( postDto.getDiary_subtitle() );
        diary.content( postDto.getContent() );
        diary.diaryImgUrl( postDto.getDiaryImgUrl() );
        diary.member( postDto.getMember() );

        return diary.build();
    }

    @Override
    public DiaryResponseDto diaryToResponseDto(Diary diary) {
        if ( diary == null ) {
            return null;
        }

        DiaryResponseDtoBuilder diaryResponseDto = DiaryResponseDto.builder();

        diaryResponseDto.diaryId( diary.getDiaryId() );
        diaryResponseDto.diary_title( diary.getDiary_title() );
        diaryResponseDto.diary_subtitle( diary.getDiary_subtitle() );
        diaryResponseDto.content( diary.getContent() );
        diaryResponseDto.member( diary.getMember() );
        diaryResponseDto.diaryImgUrl( diary.getDiaryImgUrl() );
        diaryResponseDto.comments( commentListToResponseDtoList( diary.getComments() ) );
        diaryResponseDto.category( diary.getCategory() );

        return diaryResponseDto.build();
    }

    @Override
    public List<DiaryResponseDto> diaryListToResponseDtoList(List<Diary> diaries) {
        if ( diaries == null ) {
            return null;
        }

        List<DiaryResponseDto> list = new ArrayList<DiaryResponseDto>( diaries.size() );
        for ( Diary diary : diaries ) {
            list.add( diaryToResponseDto( diary ) );
        }

        return list;
    }

    protected MemberResponseDto memberToMemberResponseDto(Member member) {
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

    protected ResponseDto commentToResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        ResponseDtoBuilder responseDto = ResponseDto.builder();

        responseDto.commentId( comment.getCommentId() );
        responseDto.content( comment.getContent() );
        responseDto.createdAt( comment.getCreatedAt() );
        responseDto.modifiedAt( comment.getModifiedAt() );
        responseDto.member( memberToMemberResponseDto( comment.getMember() ) );

        return responseDto.build();
    }

    protected List<ResponseDto> commentListToResponseDtoList(List<Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<ResponseDto> list1 = new ArrayList<ResponseDto>( list.size() );
        for ( Comment comment : list ) {
            list1.add( commentToResponseDto( comment ) );
        }

        return list1;
    }
}
