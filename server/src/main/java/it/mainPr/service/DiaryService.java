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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryMapper diaryMapper;
    private final MemberService memberService;
    private final BookRepository bookRepository;

//    public Diary createdDiary(DiariesDto.PostDto postDto, long bookId) {
//        String memberEmail = SecurityUtils.getCurrentMemberEmail();
//        Member member = memberService.findVerifiedMember(memberEmail);
//
//        Book book = bookRepository.findById(bookId).orElseThrow(() ->
//                 new BusinessLogicalException(ExceptionCode.BOOK_NOT_FOUND));
//
//        Diary diary = diaryMapper.postDtoToDiary(postDto);
//        diary.setMember(member);
//        diary.setBook(book);
//        diaryRepository.save(diary);
//        return diaryMapper.diaryToResponseDto(diary);
//    }

    public Diary createdDiary(Diary diary) {
        return diaryRepository.save(diary);
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
    public Diary deleteDiary(Diary diary) {
        Diary findDiary = findVerifiedDiary(diary.getDiaryId()); // diary가 DB에 없으면 예외처리.

        // diaryId로 diary를 불러와서 diaryStatus를'존재하지 않음'상태로 변경
        findDiary.setDiaryStatus(Diary.DiaryStatus.DIARY_NOT_EXIST);

        return findDiary;
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




//    public Page<Diary> findDiaries(int page, int size, String sort, String category) {
//
//        List<Diary> diaries = diaryRepository.findByCategoryAndDiaryStatus(
//                category,
//                Diary.DiaryStatus.DIARY_EXIST);
//
//        Comparator<Diary> comparator;
//        if(sort.equals("createdAt")) {//최신순 정렬
//            comparator = new Comparator<Diary>() {
//                @Override
//                public int compare(Diary o1, Diary o2) {
//                    return o1.getCreatedAt().isBefore(o2.getCreatedAt()) ? 1 : -1;
//                }
//            };
//        } else if(sort.equals("commentCount")) {//댓글수 순 정렬
//            comparator = new Comparator<Diary>() {
//                @Override
//                public int compare(Diary o1, Diary o2) {
//
//                    Integer count1 = o1.getComments().stream().filter(comment -> comment.getCommentStatus() == Comment.CommentStatus.COMMENT_EXIST).
//                            collect(Collectors.toList()).size();
//                    Integer count2 = o2.getComments().stream().filter(comment -> comment.getCommentStatus() == Comment.CommentStatus.COMMENT_EXIST).
//                            collect(Collectors.toList()).size();
//
//                    return (count1 < count2) ? 1 : -1;
//                }
//            };
//        } else if(sort.equals("heatCount")) {//댓글수 순 정렬
//            comparator = new Comparator<Diary>() {
//                @Override
//                public int compare(Diary o1, Diary o2) {
//
//                    Integer count1 = o1.getHeart().stream().filter(heart -> heart.getHeartStatus() == Heart.HeartStatus.HEART_EXIST).
//                            collect(Collectors.toList()).size();
//                    Integer count2 = o2.getHeart().stream().filter(heart -> heart.getHeartStatus() == Heart.HeartStatus.HEART_EXIST).
//                            collect(Collectors.toList()).size();
//
//                    return (count1 < count2) ? 1 : -1;
//                }
//            };
//        } else { //sort의 쿼리스트링 파라미터가 올바른 값이 아님
//                throw new BusinessLogicalException(ExceptionCode.SORT_NOT_FOUND);
//            }
//            Collections.sort(diaries, comparator);
//
//
//            PageRequest pageRequest =PageRequest.of(page,size);
//            int start = (int)pageRequest.getOffset();
//            int end = Math.min((start + pageRequest.getPageSize()), diaries.size());
//            Page<Diary> pagingDiaries = new PageImpl<>(diaries.subList(start, end), pageRequest, diaries.size());
//
//            return pagingDiaries;
//
//        }

}
