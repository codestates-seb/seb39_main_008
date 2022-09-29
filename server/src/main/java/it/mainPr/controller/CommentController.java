package it.mainPr.controller;

import it.mainPr.dto.CommentsDto;
import it.mainPr.dto.MultiResponseDto;
import it.mainPr.mapper.CommentMapper;
import it.mainPr.model.Comment;
import it.mainPr.model.Member;
import it.mainPr.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentMapper commentMapper;
    private final CommentService commentService;

    @PostMapping("/api/v1/comments/")
    public ResponseEntity postComment(@PathVariable Long diaryId, @RequestBody CommentsDto.PostDto postDto,
                                      Authentication authentication) {
        Member member = (Member)authentication.getPrincipal();

        Comment comment = commentMapper.postDtoToComment(postDto);

        Comment createdComment = commentService.writeComment(diaryId, comment, member);

        return new ResponseEntity(commentMapper.commentToDtoResponse(createdComment), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/comments/{comment_id}")
    public ResponseEntity getComment(@PathVariable("comment_id") long commentId) {
        Comment comment = commentService.findComment(commentId);

        return new ResponseEntity<>(commentMapper.commentToDtoResponse(comment), HttpStatus.OK);
    }

    @GetMapping("/api/v1/comments")
    public ResponseEntity getComments(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<Comment> commentPage = commentService.findComments(page - 1, size);
        List<Comment> commentList = commentPage.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(commentMapper.multiCommentToDtoResponse(commentList),commentPage), HttpStatus.OK);
    }

    @PatchMapping("/api/v1/comments/{comments_id}")
    public ResponseEntity patchComment(@PathVariable("comment_id") long commentId,
                                       @RequestBody CommentsDto.PatchDto patchDto) {

        commentService.updateComment(commentId, patchDto);
        Comment comment = commentService.findComment(commentId);

        return new ResponseEntity<>(commentMapper.commentToDtoResponse(comment), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/comments/{comments_id}")
    public ResponseEntity deleteComment(@PathVariable("comment_id") long commentId) {

        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
