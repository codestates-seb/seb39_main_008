package it.mainPr.repository;

import it.mainPr.model.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
//    boolean existsByMemberIdAndDiaryID(Long memberId, Long diaryId);
//    Optional<Heart> findByMemberIdAndDiaryId(Long memberId, Long diaryId);
//    int countByDiaryId(Long diaryId);

//    @Query("select h from Heart h where h.member_id = :memberId and h.diary_id in :diaries")
//    List<Heart> findByMemberIdAndDiaryIds(@Param("member_id") Long memberId,
//                                          @Param("diary_id") Long diaryId);
}
