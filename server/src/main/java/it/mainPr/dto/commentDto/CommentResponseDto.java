package it.mainPr.dto.commentDto;

import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {

    private Long commentId;
    private Comment.CommentStatus commentStatus;
    private String content;
    private MemberResponseDto member;
    private Long diaryId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
