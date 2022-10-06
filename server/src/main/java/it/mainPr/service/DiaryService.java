package it.mainPr.service;

import it.mainPr.auth.utils.SecurityUtils;
import it.mainPr.dto.diaryDto.DiariesDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.mapper.DiaryMapper;
import it.mainPr.model.Book;
import it.mainPr.model.Diary;
import it.mainPr.model.DiaryImage;
import it.mainPr.model.Member;
import it.mainPr.repository.BookRepository;
import it.mainPr.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryMapper diaryMapper;
    private final MemberService memberService;
    private final BookRepository bookRepository;


    public Diary createdDiary(Diary diary) {
        verifyExistDiary(diary.getMember(), diary.getBook());

        return diaryRepository.save(diary);
    }

    private void verifyExistDiary(Member member, Book book) {
        Optional<Diary> diary = diaryRepository.findByMemberAndBookAndDiaryStatus(member, book, Diary.DiaryStatus.DIARY_EXIST);

    }


    public Member findDiaryMember(long diaryId) {
        Diary findDiary = findVerifiedDiary(diaryId);
        return findDiary.getMember();
    }

    public Diary findVerifiedDiary(long diaryId) {
        Optional<Diary> optionalDiary = diaryRepository.findById(diaryId);

        // DB에 저장된 diary 정보 없으면 예외 발생
        Diary findDiary = optionalDiary.orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.DIARY_NOT_FOUND));

        // 만일 삭제된 diary라면 예외발생
        if(findDiary.getDiaryStatus() == Diary.DiaryStatus.DIARY_NOT_EXIST) {
            throw new BusinessLogicalException(ExceptionCode.DIARY_NOT_FOUND);
        }

        return findDiary;
    }

    public Member findMemberAtDiary(long diaryId){
        Diary findDiary = findVerifiedDiary(diaryId);
        return findDiary.getMember();
    }

    public Page<Diary> findDiaries(MemberService memberService, int page, int size) { // 해당 유저가 쓴 글 페이지네이션 최신순 구현
        Member member = memberService.getLoginMember();
        Page<Diary> diaries = diaryRepository.findByMemberAndDiaryStatus(
                PageRequest.of(page,size, Sort.by("createdAt").descending()), member, Diary.DiaryStatus.DIARY_EXIST);

        return diaries;
    }

    @Transactional
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

    @Transactional
    public Diary updateDiary(Diary diary) {
        Diary findDiary = findVerifiedDiary(diary.getDiaryId()); // diary가 DB에 없으면 예외처리.

        // 삭제된 (실제로는 not exist 상태인) diary라면 예외처리.
        if(findDiary.getDiaryStatus() == Diary.DiaryStatus.DIARY_NOT_EXIST){
            throw new BusinessLogicalException(ExceptionCode.DIARY_NOT_FOUND);
        }


        log.info("게시글 존재함 {}",diary.getDiaryId().toString());

        Optional.ofNullable(diary.getContent()) // 본문 수정
                .ifPresent(findDiary::setContent);

        Optional.ofNullable(diary.getModifiedAt()) // 업데이트 날짜 수정
                .ifPresent(findDiary::setModifiedAt);

        Optional.ofNullable(diary.getDiaryStatus()) // 글 삭제 (실제로 삭제하는 것이 아니라 Status를 변경시키는 것...)
                .ifPresent(findDiary::setDiaryStatus);
//

        Optional.ofNullable(diary.getDiaryImgUrl())//스레드 이미지 수정
                .ifPresent(diaryImages -> { //StoreImages null값 아닐때!
                    System.out.println("들어올수없는 이미지 입니다");
                    findDiary.getDiaryImgUrl().stream().forEach(diaryImage -> //기존 스레드이미지 삭제(STORE_IMAGE_NOT_EXIST)됌
                            diaryImage.setDiaryImageStatus(DiaryImage.DiaryImageStatus.DIARY_IAMGE_NOT_EXIST));

                    diary.getDiaryImgUrl().stream().forEach(diaryImage -> //새 스레드 이미지로 갱신
                            findDiary.getDiaryImgUrl().add(diaryImage));
                });

//        if(diary.getDiaryImages().isEmpty()) {
//            // 수정된 글에 이미지가 없다면, 등록되어 있는 이미지는 삭제 (= 사용하지 않는 상태로 변경)
//            diaryImageService.deleteDiaryImages(diary);
//
//        } else {
//            log.info("이미지 수정 {}",diary.getDiaryImages().toString()); // 새롭게 올라온 이미지가 있다면
//            diaryImageService.deleteDiaryImages(diary); // 등록되어 있는 이미지는 삭제 (= 사용하지 않는 상태로 변경)
//            diaryImageService.createDiaryImages(diary.getDiaryImages()); // 이후 새롭게 추가된 이미지 등록
//        }
//
//        updatedDiary.setDiaryImages(diaryImageService.findVerifiedDiaryImages(updatedDiary));

        return findDiary;
    }

    public Page<Diary> findAllDiaries(int page, int size, String sort) {

        if (sort.equals("createdAt")) { // 최신순 정렬
            Page<Diary> diaries = diaryRepository.findByDiaryStatus(
                    PageRequest.of(page, size, Sort.by(sort).descending()),
                    Diary.DiaryStatus.DIARY_EXIST);

            System.out.println(diaries.getTotalElements());
            System.out.println(sort);

            return diaries;
        } else {
            throw new BusinessLogicalException(ExceptionCode.SORT_NOT_FOUND);
        }
    }

    public Page<Diary> findExistDiariesToPaginationAndSort(Book book, Integer diaryPage,Integer diarySize,String diarySort) {
        Page<Diary> findDiaries = diaryRepository.findByBookAndDiaryStatus(
                PageRequest.of(diaryPage-1,diarySize, Sort.by(diarySort).descending()),
                book, Diary.DiaryStatus.DIARY_EXIST
        );
        return findDiaries;
    }

    public List<Diary> findExistDiaries(List<Diary> diaries){
        return diaries.stream().map(diary -> diaryRepository.findByDiaryIdAndDiaryStatus(
                diary.getDiaryId(),Diary.DiaryStatus.DIARY_EXIST)).collect(Collectors.toList());
    }
}
