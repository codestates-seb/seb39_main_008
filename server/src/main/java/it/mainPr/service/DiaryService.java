package it.mainPr.service;

import it.mainPr.auth.utils.SecurityUtils;
import it.mainPr.dto.diaryDto.DiariesDto;
import it.mainPr.dto.global.MultiResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.mapper.DiaryMapper;
import it.mainPr.model.Book;
import it.mainPr.model.Diary;
import it.mainPr.model.Member;
import it.mainPr.repository.BookRepository;
import it.mainPr.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryMapper diaryMapper;
    private final MemberService memberService;
    private final BookRepository bookRepository;

    public DiariesDto.DiaryResponseDto writeDiary(long bookId, DiariesDto.PostDto postDto) {
        String memberEmail = SecurityUtils.getCurrentMemberEmail();
        Member member = memberService.findVerifiedMember(memberEmail);

        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                 new BusinessLogicalException(ExceptionCode.BOOK_NOT_FOUND));

        Diary diary = diaryMapper.postDtoToDiary(postDto);
        diary.setMember(member);
        diary.setBook(book);
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
        Member member = (Member) authentication.getPrincipal();
        if(diary.getMember().getMemberId() != member.getMemberId()) {
            throw new BusinessLogicalException(ExceptionCode.NO_AUTHORIZED);
        }
    }
    public Diary findVerifiedDiary(long diaryId){
        Optional<Diary> optionalDiary = diaryRepository.findById(diaryId);

        Diary findDiary=optionalDiary.orElseThrow(()-> //만일 db에 저장된 스토어 정보 없으면 예외발생
                new BusinessLogicalException(ExceptionCode.DIARY_NOT_FOUND));

        if(findDiary.getDiaryStatus() == Diary.DiaryStatus.DIARY_NOT_EXIST){// 만일 삭제된 스토어라면 예외발생
            throw new BusinessLogicalException(ExceptionCode.DIARY_NOT_FOUND);
        }
        return findDiary;
    }

    public Member findMemberAtDiary(long diaryId){//해당 스토어의 주인유저 반환
        Diary findDiary = findVerifiedDiary(diaryId);//만약 스토어가 DB에 없거나 삭제된 스토어면 예외 발생
        return findDiary.getMember();
    }


}