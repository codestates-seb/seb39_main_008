package it.mainPr.dto.commentDto;

import it.mainPr.model.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentPatchDto {

    private String content;
    private Comment.CommentStatus commentStatus;
}
