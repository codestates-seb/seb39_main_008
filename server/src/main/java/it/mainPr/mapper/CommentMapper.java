package it.mainPr.mapper;

import it.mainPr.dto.CommentsDto;
import it.mainPr.model.Comment;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    //Post
    Comment postDtoToComment(CommentsDto.PostDto postDto);

    //Patch
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CommentsDto.PatchDto patchDto, @MappingTarget Comment entity);

    //Response
    CommentsDto.ResponseDto commentToResponseDto(Comment comment);
    List<CommentsDto.ResponseDto> multiCommentToDtoResponse(List<Comment> comments);
}

