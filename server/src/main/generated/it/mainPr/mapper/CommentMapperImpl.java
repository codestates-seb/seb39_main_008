package it.mainPr.mapper;

import it.mainPr.dto.commentDto.CommentsDto.PatchDto;
import it.mainPr.dto.commentDto.CommentsDto.PostDto;
import it.mainPr.dto.commentDto.CommentsDto.ResponseDto;
import it.mainPr.dto.commentDto.CommentsDto.ResponseDto.ResponseDtoBuilder;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.dto.memberDto.MemberResponseDto.MemberResponseDtoBuilder;
import it.mainPr.model.Comment;
import it.mainPr.model.Comment.CommentBuilder;
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
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment postDtoToComment(PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        CommentBuilder comment = Comment.builder();

        comment.content( postDto.getContent() );

        return comment.build();
    }

    @Override
    public void updateEntityFromDto(PatchDto patchDto, Comment entity) {
        if ( patchDto == null ) {
            return;
        }

        if ( patchDto.getContent() != null ) {
            entity.setContent( patchDto.getContent() );
        }
    }

    @Override
    public ResponseDto commentToDtoResponse(Comment comment) {
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

    @Override
    public List<ResponseDto> multiCommentToDtoResponse(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<ResponseDto> list = new ArrayList<ResponseDto>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( commentToDtoResponse( comment ) );
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
}
