package it.mainPr.service;


import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.model.Comment;
import it.mainPr.model.Diary;
import it.mainPr.model.Member;
import it.mainPr.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Page<Comment> findExistCommentsToPaginationAndSort(
            Diary diary, Integer commentPage, Integer commentSize, String commentSort) { // diary의 comment중 status가 true인 것만 페이지네이션 정렬해서 반환
        Page<Comment> findReplies = commentRepository.findByDiaryAndCommentStatus(
                PageRequest.of(commentPage-1, commentSize, Sort.by(commentSort).descending()),
                diary, Comment.CommentStatus.COMMENT_EXIST
        );
        return findReplies;
    }

    public List<Comment> findExistComments(List<Comment> replies) { // comment중 status가 true인 것만 반환
        return replies.stream().map(comment -> commentRepository.findByCommentIdAndCommentStatus(
                comment.getCommentId(), Comment.CommentStatus.COMMENT_EXIST)).collect(Collectors.toList());
    }


}
