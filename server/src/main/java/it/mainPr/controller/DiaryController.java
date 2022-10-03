package it.mainPr.controller;

import it.mainPr.dto.diaryDto.DiariesDto;
import it.mainPr.dto.global.MultiResponseDto;
import it.mainPr.mapper.DiaryMapper;
import it.mainPr.model.Diary;
import it.mainPr.model.Member;
import it.mainPr.repository.DiaryRepository;
import it.mainPr.service.CommentService;
import it.mainPr.service.DiaryService;
import it.mainPr.service.HeartService;
import it.mainPr.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
    public class DiaryController {
    private final DiaryService diaryService;
    private final DiaryMapper diaryMapper;
    private final DiaryRepository diaryRepository;
    private final HeartService heartService;
    private final CommentService commentService;

    @PostMapping("/api/v1/diaries")
    public ResponseEntity writeDiary(@Valid @RequestBody DiariesDto.PostDto postDto) {
        Diary diary = diaryMapper.postDtoToDiary(postDto);
        DiariesDto.DiaryResponseDto responseDto = diaryService.writeDiary(postDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @GetMapping("/api/v1/diaries/{diary_id}")
    public ResponseEntity getDiary(@PathVariable("diary_id") Long diaryId) {
        DiariesDto.DiaryResponseDto responseDto = diaryService.readDiary(diaryId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/api/v1/diaries")
    public ResponseEntity getAllDiaries(@PageableDefault(page = 1, sort = "diaryId", direction = Sort.Direction.DESC) Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
        MultiResponseDto responseDto = diaryService.readAllDiaries(pageRequest);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/api/v1/diaries/{diary_id}")
    public ResponseEntity patchDiary(@PathVariable("diary_id") Long diaryId, @RequestBody DiariesDto.PatchDto patchDto, Authentication authentication) {
        DiariesDto.DiaryResponseDto responseDto = diaryService.updateDiary(diaryId, patchDto, authentication);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/diaries/{diary_id}")
    public ResponseEntity deleteDiary(@PathVariable("diary_id") Long diaryId, Authentication authentication) {
        diaryService.deleteDiary(diaryId, authentication);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
