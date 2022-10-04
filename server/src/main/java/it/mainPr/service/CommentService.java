package it.mainPr.service;


import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.model.Comment;
import it.mainPr.model.Member;
import it.mainPr.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(Comment comment) {
        Comment findComment = findVerifiedComment(comment.getCommentId());

        if(findComment.getCommentStatus() == Comment.CommentStatus.COMMENT_NOT_EXIST) {
            throw new BusinessLogicalException(ExceptionCode.COMMENT_NOT_FOUND);
        }

        log.info("댓글이 존재함 {}", comment.getCommentId().toString());

        Optional.ofNullable(comment.getContent())
                .ifPresent(findComment::setContent);

        Optional.ofNullable(comment.getModifiedAt())
                .ifPresent(findComment::setModifiedAt);

        Optional.ofNullable(comment.getCommentStatus())
                .ifPresent(findComment::setCommentStatus);

        return findComment;

    }

    public Comment findVerifiedComment(long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment findComment = optionalComment.orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.COMMENT_NOT_FOUND));
        return findComment;
    }

    public Member findCommentMember(long commentId) {
        Comment findComment = findVerifiedComment(commentId);
        return findComment.getMember();
    }

    @Transactional
    public Comment deleteComment(Comment comment) {
        Comment findComment = findVerifiedComment(comment.getCommentId());

        findComment.setCommentStatus(Comment.CommentStatus.COMMENT_NOT_EXIST);

        return findComment;
    }

}
