package it.mainPr.comment;

import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
