package it.mainPr.repository;

import it.mainPr.model.Diary;
import it.mainPr.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findByCategoryAndDiaryStatus(String category, Diary.DiaryStatus diaryStatus);

    @Query(value = "select * from diary s where (s.status= :status and s.category= :category) and " +
            "(upper(s.name) like upper(concat('%',:keyword,'%')) or " +
            "upper(s.content) like upper(concat('%',:keyword,'%')))", nativeQuery = true)
    List<Diary> searchDiaryByCategoryAndKeyword(@Param("category") String category, @Param("keyword") String keyword, @Param("status")String storeStatus);

    List<Diary> findByMemberAndDiaryStatus(Member member, Diary.DiaryStatus diaryStatus);

    Page<Diary> findByMemberAndDiaryStatus(Pageable pageable, Member member, Diary.DiaryStatus diaryStatus);

    Page<Diary> findByDiaryStatus(Pageable pageable, Diary.DiaryStatus diaryStatus);

}