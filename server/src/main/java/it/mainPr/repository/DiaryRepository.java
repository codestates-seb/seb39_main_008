package it.mainPr.repository;

import it.mainPr.model.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
//    Page<Diary> findByTitleContaining(String title, Pageable pageable);
}