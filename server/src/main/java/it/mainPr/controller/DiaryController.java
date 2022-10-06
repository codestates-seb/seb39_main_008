package it.mainPr.controller;

import it.mainPr.dto.diaryDto.DiariesDto;
import it.mainPr.dto.global.MultiResponseDto;
import it.mainPr.dto.global.SingleResponseDto;
import it.mainPr.mapper.CommentMapper;
import it.mainPr.mapper.DiaryMapper;
import it.mainPr.mapper.MemberMapper;
import it.mainPr.model.Diary;
import it.mainPr.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
    public class DiaryController {
    private final DiaryService diaryService;
    private final DiaryMapper diaryMapper;
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final HeartService heartService;
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final DiaryImageService diaryImageService;
    private final BookService bookService;


    // mapper 활용
    @PostMapping("/api/v1/diaries")
    public ResponseEntity createdDiary(@Positive @RequestParam(name = "book_id") long bookId,
                                       @Valid @RequestBody DiariesDto.PostDto postDto) {

        Diary diary = diaryService.createdDiary(
                diaryMapper.diaryPostDtoToDiary(bookService, memberService, bookId, postDto));

        return new ResponseEntity<>(new SingleResponseDto<>(
                diaryMapper.diaryToDiaryResponse(memberMapper, diary)),HttpStatus.CREATED);

    }

    @PatchMapping("/api/v1/diaries/{diary_id}")
    public ResponseEntity patchDiary(@PathVariable("diary_id") @Positive @NotNull long diaryId,
                                      @Valid @RequestBody DiariesDto.PatchDto patchDto) {
        // Request를 처리하기 위한 객체 생성. / 객체를 생성하는 메서드는 diaryService에서 정의, 생성 메서드의 매개변수 생성은 mapper에서 만든다.
        Diary diary = diaryMapper.diaryPatchDtoToDiary(diaryService, memberService, diaryId, patchDto);
        Diary updatedDiary = diaryService.updateDiary(diary);

        // 생성된 객체를 처리하여 Response 반환
        return new ResponseEntity<>(
                new SingleResponseDto<>(diaryMapper.diaryToDiaryResponse(
                        memberMapper, updatedDiary)),HttpStatus.OK);
    }



//    @PatchMapping("/api/v1/diaries/{diary_id}")
//    public ResponseEntity deleteDiary(@PathVariable("diary_id") @Positive @NotNull long diaryId,
//                                       @Valid @RequestBody DiariesDto.PatchDto patchDto) {
//        // Request를 처리하기 위한 객체 생성. / 객체를 생성하는 메서드는 diaryService에서 정의, 생성 메서드의 매개변수 생성은 mapper에서 만든다.
//        // 실제로 삭제하는 것이 아니라 diaryStatus를 THREAD_NOT_EXIST로 변경하는 것.
//        Diary diary = diaryMapper.diaryPatchDtoToDiary(
//                diaryService, memberService, diaryId, patchDto);
//        Diary deletedDiary = diaryService.deleteDiary(diary);
//
//        // 생성된 객체를 처리하여 Response 반환
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(diaryMapper.diaryToDiaryResponseDto(
//                        commentService, heartService, commentMapper, memberMapper,
//                        diaryImageService, deletedDiary)),
//                HttpStatus.OK);
//    }

    //전체 카테고리별 부르기
//    @GetMapping("/api/v1/diaries/category")
//    public ResponseEntity getAllDiaries(@Pattern(regexp = "(^일상 공유$)|(^공감과 치유$)|(^문화 생활$)|(^여행기록$)|(^자유$)",
//            message = "카테고리중 고르세요") @RequestParam("category") String category,
//                                        @Positive @RequestParam("page") int page,
//                                        @Positive @RequestParam("size") int size,
//                                        @Pattern(regexp = "(^createdAt$)|(^commentCount$)|(^heartCount$)",
//                                        message = "createdAt, CommentCount, HeartCount 고르세요")
//                                        @RequestParam("sort") String sort) {
//        Page<Diary> pageDiaries = diaryService.findDiaries(page-1, size, sort, category);
//
//        List<Diary> diaries = pageDiaries.getContent();
//
//        return new ResponseEntity<>(new MultiResponseDto<>(diaryMapper.diaryToDiaryResponseDtos(diaryService, heartService, diaryMapper, memberMapper, diaries), pageDiaries), HttpStatus.OK);
//    }

//    @GetMapping("/api/v1/diary/{diary-id}")
//    public ResponseEntity getDiary(@PathVariable("diary-id") @Positive @NotNull Long diaryId,
//                                    @Positive @RequestParam("page") Integer commentPage,
//                                    @Positive @RequestParam("size") Integer commentSize,
//                                    @RequestParam("sort") String commentSort) {
//        Diary diary = diaryService.findVerifiedDiary(diaryId);
//
//        return new ResponseEntity<>(new SingleResponseDto<>(
//                diaryMapper.diaryToDiaryAndCommentResponseDto(
//                        commentService, heartService, commentMapper, memberMapper,
//                        diaryImageService, diary, commentPage, commentSize, commentSort)),
//                HttpStatus.OK);
//    }
    /**
     * 유저 자신이 쓴 글 리스트 가져오기 API
     * **/
    @GetMapping("/api/v1/diaries/mine")
    public ResponseEntity getDiaries(@Positive @RequestParam("page") int page,
                                     @Positive @RequestParam("size") int size){

        Page<Diary> pageDiaries = diaryService.findDiaries(memberService,page-1,size);

        List<Diary> diaries = pageDiaries.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(
                diaryMapper.diaryTodiaryesponseDtos(
                        commentService, heartService, commentMapper,
                        memberMapper, diaryImageService, diaries),
                pageDiaries),HttpStatus.OK);
    }

    //모든 글 가져오기
    @GetMapping("/api/v1/diaries")
    public ResponseEntity getAllDiaries(@Positive @RequestParam("page") int page,
                                        @Positive @RequestParam("size") int size,
                                        @RequestParam("sort") String sort) {

        Page<Diary> pageDiaries = diaryService.findAllDiaries(page-1, size, sort);
        List<Diary> diaries = pageDiaries.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(
                diaryMapper.diaryTodiaryesponseDtos(
                        commentService, heartService, commentMapper, memberMapper,
                        diaryImageService, diaries),
                pageDiaries), HttpStatus.OK);
    }


    @GetMapping("/api/v1/diaries/{diary_id}")
    public ResponseEntity getDiary(@PathVariable("diary_id") long diaryId) {
        Diary diary = diaryService.findVerifiedDiary(diaryId);

        return new ResponseEntity<>(new SingleResponseDto<>(diaryMapper.diaryToDiaryResponseDto(
                commentService, heartService, commentMapper, memberMapper, diaryImageService, diary)), HttpStatus.OK);
    }
    @DeleteMapping("/api/v1/diaries/{diary_id}")
    public ResponseEntity deleteDiary(@PathVariable("diary_id") Long diaryId, Authentication authentication) {
        diaryService.deleteDiary(diaryId, authentication);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
