package it.mainPr.service;

import it.mainPr.model.Diary;
import it.mainPr.model.DiaryImage;
import it.mainPr.repository.DiaryImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryImageService {

    DiaryImageRepository diaryImageRepository;

    public DiaryImageService(DiaryImageRepository diaryImageRepository) {
        this.diaryImageRepository = diaryImageRepository;
    }


    public List<DiaryImage> findVerifiedDiaryImages(Diary diary) {
        List<DiaryImage> findDiaryImages = diaryImageRepository.findAllByDiaryAndDiaryImageStatus(
                diary, DiaryImage.DiaryImageStatus.DIARY_IMAGE_EXIST);
        return findDiaryImages;
    }
}
