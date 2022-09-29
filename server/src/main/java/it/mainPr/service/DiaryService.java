package it.mainPr.service;

import it.mainPr.auth.PrincipalDetails;
import it.mainPr.dto.DiariesDto;
import it.mainPr.dto.MultiResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.mapper.DiaryMapper;
import it.mainPr.model.Diary;
import it.mainPr.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryMapper diaryMapper;

    public DiariesDto.DiaryResponseDto writeDiary(DiariesDto.PostDto postDto, Authentication authentication) {
        Diary diary = diaryMapper.postDtoToDiary(postDto);
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        diary.setMember(principalDetails.getMember());
        diaryRepository.save(diary);
        //이미지 클래스 추가 후 주석 제거
//        if(postDto.getDiaryImgUrl() != null) {
//            saveImages(postDto.getDiaryImgUrl(), diary);
//        }
        return diaryMapper.diaryToResponseDto(diary);

    }

    public DiariesDto.DiaryResponseDto readDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.DIARY_NOT_FOUND));
        return diaryMapper.diaryToResponseDto(diary);
    }
    public MultiResponseDto readAllDiaries(PageRequest pageRequest) {
        Page<Diary> diaryPage = diaryRepository.findAll(pageRequest);
        List<Diary> diaryList = diaryPage.getContent();
        return new MultiResponseDto(diaryMapper.diaryListToResponseDtoList(diaryList), diaryPage);
    }

    public DiariesDto.DiaryResponseDto updateDiary(Long diaryId, DiariesDto.PatchDto patchDto, Authentication authentication) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.DIARY_NOT_FOUND));
        checkPermission(diary, authentication);

        diary.updateDiaries(patchDto.getContent());

        diaryRepository.save(diary);
        return diaryMapper.diaryToResponseDto(diary);
    }



    public void deleteDiary(Long diaryId, Authentication authentication) {
       Diary diary = diaryRepository.findById(diaryId).orElseThrow(() ->
               new BusinessLogicalException(ExceptionCode.DIARY_NOT_FOUND));
       checkPermission(diary, authentication);
       diaryRepository.deleteById(diaryId);
    }

    public void checkPermission(Diary diary, Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        if(diary.getMember().getMemberId() != principalDetails.getMember().getMemberId()) {
            throw new BusinessLogicalException(ExceptionCode.NO_AUTHORIZED);
        }
    }
}