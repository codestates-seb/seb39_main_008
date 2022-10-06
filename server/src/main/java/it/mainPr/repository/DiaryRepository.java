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

    Page<Diary> findByMemberAndDiaryStatus(Pageable pageable, Member member, Diary.DiaryStatus diaryStatus);

    Page<Diary> findByDiaryStatus(Pageable pageable, Diary.DiaryStatus diaryStatus);

}