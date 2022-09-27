package it.mainPr.controller;

import it.mainPr.dto.commonDto.MultiResponseDto;
import it.mainPr.dto.commonDto.SingleResponseDto;
import it.mainPr.dto.diary.DiaryRequestDto;
import it.mainPr.dto.diary.DiaryResponseDto;
import it.mainPr.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diaries")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/api/v1/diaries")
    @CrossOrigin("*")
    public ResponseEntity writeDiary(@RequestBody DiaryRequestDto diaryRequestDto) {
        DiaryResponseDto response = diaryService.saveDiary(diaryRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity updateDiary(@PathVariable Long diaryId,
                                      @RequestBody DiaryRequestDto diaryRequestDto) {
        DiaryResponseDto response = diaryService.updateDiary(diaryId, diaryRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity findByDiary(@PathVariable Long diaryId) {
        DiaryResponseDto response = diaryService.findById(diaryId);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity findAllDiary() {
        List<DiaryResponseDto> response = diaryService.findAll();
        return new ResponseEntity<>(new MultiResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteDiary(@PathVariable Long diaryId) {
        diaryService.deleteDiary(diaryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
