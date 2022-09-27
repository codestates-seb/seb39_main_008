package it.mainPr.service;

import it.mainPr.dto.diary.DiaryRequestDto;
import it.mainPr.dto.diary.DiaryResponseDto;
import it.mainPr.model.Diary;
import it.mainPr.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    @Transactional
    public DiaryResponseDto saveDiary(DiaryRequestDto diaryRequestDto) {
        Diary entity = diaryRepository.save(diaryRequestDto.toEntity());
        return new DiaryResponseDto(entity);
    }

    @Transactional
    public DiaryResponseDto updateDiary(Long diaryId, DiaryRequestDto diaryRequestDto) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(
                () -> new IllegalArgumentException("DiaryId: Diary does not Exist"));

        diary.update(diaryRequestDto.getTitle(),
                diaryRequestDto.getSubtitle(),
                diaryRequestDto.getContent(),
                diaryRequestDto.getDiaryImgUrl());

        return new DiaryResponseDto(diary);
    }

    public DiaryResponseDto findById(Long diaryId) {
        Diary entity = diaryRepository.findById(diaryId).orElseThrow(
                () -> new IllegalArgumentException("DiaryId: Diary does not exist"));

        return new DiaryResponseDto(entity);
    }

    public List<DiaryResponseDto> findAll() {
        return diaryRepository.findAll().stream()
                .map(diary -> new DiaryResponseDto(diary)).collect(Collectors.toList());
    }

    public void deleteDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(
                () -> new IllegalArgumentException("DiaryId: Diary does not exist"));
        diaryRepository.delete(diary);
    }
}
