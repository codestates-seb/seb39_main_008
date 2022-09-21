package it.mainPr.diary.service;

import it.mainPr.diary.entity.Diary;
import it.mainPr.diary.repository.DiaryRepository;
import it.mainPr.exception.BusinessLogicException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.member.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@AllArgsConstructor
public class DiaryService{

    @Autowired
    private final DiaryRepository diaryRepository;

    // 기록 작성
    @Transactional
    public Diary diary_write(Diary diary, Member member){
        diary.setMember(member);
        return diaryRepository.save(diary);
    }

    //기록 리스트
    @Transactional(readOnly = true)
    public Page<Diary> diaryList(Pageable pageable){

        return diaryRepository.findAll(pageable);
    }

    // 특정 기록 불러오기
    @Transactional(readOnly = true)
    public Diary Diary_View(Long diaryId) {
        Optional<Diary> optionalDiary =
                diaryRepository.findById(diaryId);

        Diary diary = optionalDiary.orElseThrow(() -> {
            return new BusinessLogicException(ExceptionCode.NoSuchElementException);
        });
        return diary;
    }

    @Transactional
    public Diary diaryUpdate(long diaryId, Diary requestDiary){
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("기록 찾기 실패: 해당 기록을 찾을수 없습니다.");
                });
        diary.setTitle(requestDiary.getTitle());
        diary.setContent(requestDiary.getContent());
        return diaryRepository.save(diary);
    }

    // 특정 기록 삭제하기

    @Transactional
    public void diary_delete(Long diaryId) {
        diaryRepository.deleteById(diaryId);
    }

}
