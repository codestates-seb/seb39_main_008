package it.mainPr.controller;

import it.mainPr.dto.commentDto.CommentPatchDto;
import it.mainPr.dto.commentDto.CommentPostDto;
import it.mainPr.dto.global.MultiResponseDto;
import it.mainPr.dto.global.SingleResponseDto;
import it.mainPr.mapper.CommentMapper;
import it.mainPr.mapper.MemberMapper;
import it.mainPr.model.Comment;
import it.mainPr.model.Member;
import it.mainPr.service.CommentService;
import it.mainPr.service.DiaryService;
import it.mainPr.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping
@Validated
@AllArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final DiaryService diaryService;

    @PostMapping("/api/v1/comments")
    public ResponseEntity postComment(@Positive @RequestParam("diary_id") long diaryId,
                                      @Valid @RequestBody CommentPostDto commentPostDto) {
        Comment comment = commentService.createComment(commentMapper.commentPostDtoComment(
                diaryService, memberService, diaryId, commentPostDto));

        return new ResponseEntity<>(new SingleResponseDto<>(
                commentMapper.commentToCommentResponseDto(memberMapper, comment)),HttpStatus.CREATED);
    }

    @PatchMapping("/api/v1/comments/{comment_id}")
    public ResponseEntity patchComment(@PathVariable("comment_id") @Positive @NotNull long commentId,
                                       @Valid @RequestBody CommentPatchDto commentPatchDto) {
        Comment comment = commentMapper.commentPatchDtoToComment(commentService, memberService, commentId, commentPatchDto);
        Comment updateComment = commentService.updateComment(comment);

        return new ResponseEntity<>(new SingleResponseDto<>(commentMapper.commentToCommentResponseDto(memberMapper, comment)),HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/comments/{comment_id}")
    public ResponseEntity deleteComment(@PathVariable("comment_id") @Positive @NotNull long commentId,
                                        @Valid @RequestBody CommentPatchDto commentPatchDto) {
        //실제 삭제가 아닌 commentStatus를 COMMENT_NOT_EXIST로 변경
        Comment comment = commentMapper.commentPatchDtoToComment(
                commentService, memberService, commentId, commentPatchDto);
        Comment deleteComment = commentService.deleteComment(comment);

        return new ResponseEntity<>(new SingleResponseDto<>(
                commentMapper.commentToCommentResponseDto(memberMapper, comment)), HttpStatus.OK);
    }
}
