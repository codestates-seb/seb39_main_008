package it.mainPr.diary.controller;

import it.mainPr.diary.dto.DiaryDto;
import it.mainPr.diary.dto.DiaryResponseDto;
import it.mainPr.diary.entity.Diary;
import it.mainPr.diary.mapper.DiaryMapper;
import it.mainPr.diary.service.DiaryService;
import it.mainPr.dto.SingleResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/diaries")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private DiaryMapper mapper;

//    @PostMapping
//    public ResponseEntity diaryWrite(@Valid @RequestBody DiaryDto.Post requestbody,
//                                     @AuthenticationPrincipal PrincipalDetails principal) {
//        Diary diary = mapper.diaryPostToDiary(requestbody);
//        Diary createDiary = diaryService.diary_write(diary, principal.getMember());
//        DiaryResponseDto diaryResponseDto = mapper.diaryToDiaryResponse(createDiary);
//
//        diaryResponseDto.setMemberId(principal.);
//    }

    @GetMapping
    public ResponseEntity diaryList(@PageableDefault(size = 10, sort = "diaryId", direction = Sort.Direction.DESC)
                                    Pageable pageable){
        Page<Diary> diaries = diaryService.diaryList(pageable);
        Page<DiaryResponseDto> pageDto = diaries.map(DiaryResponseDto::new);

        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }


    @GetMapping("/{diaries_id}")
    public ResponseEntity diaryView(@PathVariable("diaries_id") Long diaryId) {

        diaryService.diary_delete(diaryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{diaries_id}")
    public ResponseEntity diaryDelete(@PathVariable("diaries_id") Long diaryId){

        diaryService.diary_delete(diaryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{diaries_id}")
    public ResponseEntity diaryUpdate(@PathVariable("diaries_id") Long diaryId,
                                      @Valid @RequestBody DiaryDto.Patch requestBody){
        Diary diary = diaryService.diaryUpdate(diaryId, mapper.diaryPatchToDiary(requestBody));
        DiaryResponseDto diaryResponseDto = mapper.diaryToDiaryResponse(diary);
        diaryResponseDto.setMemberId(diary.getMember().getMemberId());
        diaryResponseDto.setTitle(diary.getTitle());
        diaryResponseDto.setContent(diary.getContent());

        return new ResponseEntity<>(
                new SingleResponseDto<>(diaryResponseDto), HttpStatus.OK);
    }
}
