package it.mainPr.repository;

import it.mainPr.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
//    @Query(value = "select c from Comment c join fetch c.diary r join fetch r.member m where m.id = :memberId",
//            countQuery = "select count(c) from Comment c diary c.diary r join r.member m where m.id = :memberId")
//    Page<Comment> findAllByDiaryWriterId(@Param("memberId") Long diaryWriterId, Pageable pageable);

//    @Query(value = "select c from Comment c join c.diary where c.diary.id = :diaryId")
//    Page<Comment> findAllByDiaryId(@Param("diaryId") Long diaryId, Pageable pageable);
}