package it.mainPr.mapper;

import it.mainPr.dto.CommentsDto.ResponseDto;
import it.mainPr.dto.CommentsDto.ResponseDto.ResponseDtoBuilder;
import it.mainPr.dto.DiariesDto.DiaryResponseDto;
import it.mainPr.dto.DiariesDto.DiaryResponseDto.DiaryResponseDtoBuilder;
import it.mainPr.dto.DiariesDto.PostDto;
import it.mainPr.dto.MemberResponseDto;
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
    date = "2022-10-01T01:40:17+0900",
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

        diary.title( postDto.getTitle() );
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
        diaryResponseDto.title( diary.getTitle() );
        diaryResponseDto.content( diary.getContent() );
        diaryResponseDto.nickname( diary.getNickname() );
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

        MemberResponseDto memberResponseDto = new MemberResponseDto();

        memberResponseDto.setMemberId( member.getMemberId() );
        memberResponseDto.setEmail( member.getEmail() );
        memberResponseDto.setName( member.getName() );
        memberResponseDto.setNickname( member.getNickname() );
        memberResponseDto.setInformation( member.getInformation() );
        memberResponseDto.setImgUrl( member.getImgUrl() );

        return memberResponseDto;
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
