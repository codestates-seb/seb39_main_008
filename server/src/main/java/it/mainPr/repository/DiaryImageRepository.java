package it.mainPr.repository;

import it.mainPr.model.Diary;
import it.mainPr.model.DiaryImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DiaryImageRepository extends JpaRepository<DiaryImage, Long> {

    List<DiaryImage> findAllByDiaryAndDiaryImageStatus(Diary diary, DiaryImage.DiaryImageStatus diaryImageStatus);
}
