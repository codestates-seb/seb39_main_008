package it.mainPr.service;


import it.mainPr.dto.CommentsDto;
import it.mainPr.mapper.CommentMapper;
import it.mainPr.model.Comment;
import it.mainPr.model.Diary;
import it.mainPr.model.Member;
import it.mainPr.repository.CommentRepository;
import it.mainPr.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentMapper mapper;
    private final CommentRepository commentRepository;
    private final DiaryRepository diaryRepository;


    public Comment writeComment(Long diaryId, Comment comment, Member member) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        comment.setDiary(diary);
        comment.setMember(member);
        return commentRepository.save(comment);
    }

    public Comment findComment(Long commentId) {
        return findVerifiedComment(commentId);
    }

    public Page<Comment> findComments(int page, int size) {
        return commentRepository.findAll(PageRequest.of(page,size, Sort.by("commentId").descending()));
    }

    public Comment updateComment(long commentId, CommentsDto.PatchDto patchDto) {
        Comment comment = findVerifiedComment(commentId);
        mapper.updateEntityFromDto(patchDto, comment);

        return commentRepository.save(comment);
    }

    public void deleteComment(long commentId) {
        Comment comment = findVerifiedComment(commentId);
        commentRepository.delete(comment);
    }

    public Comment findVerifiedComment(long commentId) {
        Optional<Comment> optionalComment =
                commentRepository.findById(commentId);
        Comment findComment = optionalComment.orElseThrow(() -> new RuntimeException("COMMENT_NOT_FOUND"));

        return findComment;
    }



}
