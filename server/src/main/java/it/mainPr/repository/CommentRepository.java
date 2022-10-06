package it.mainPr.repository;

import it.mainPr.model.Comment;
import it.mainPr.model.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByDiaryAndCommentStatus(Pageable pageable, Diary diary, Comment.CommentStatus commentStatus);

    Comment findByCommentIdAndCommentStatus(Long commentId, Comment.CommentStatus commentStatus);
}