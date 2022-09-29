package it.mainPr.mapper;

import it.mainPr.dto.CommentsDto.PatchDto;
import it.mainPr.dto.CommentsDto.PostDto;
import it.mainPr.dto.CommentsDto.ResponseDto;
import it.mainPr.dto.CommentsDto.ResponseDto.ResponseDtoBuilder;
import it.mainPr.dto.MemberResponseDto;
import it.mainPr.model.Comment;
import it.mainPr.model.Comment.CommentBuilder;
import it.mainPr.model.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-29T22:41:16+0900",
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

        MemberResponseDto memberResponseDto = new MemberResponseDto();

        memberResponseDto.setMemberId( member.getMemberId() );
        memberResponseDto.setEmail( member.getEmail() );
        memberResponseDto.setName( member.getName() );
        memberResponseDto.setNickname( member.getNickname() );
        memberResponseDto.setInformation( member.getInformation() );
        memberResponseDto.setImgUrl( member.getImgUrl() );

        return memberResponseDto;
    }
}
