package it.mainPr.controller;

import it.mainPr.dto.commonDto.MultiResponseDto;
import it.mainPr.dto.commonDto.SingleResponseDto;
import it.mainPr.dto.DiariesDto;
import it.mainPr.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/api/v1/diaries")
    @CrossOrigin("*")
    public ResponseEntity writeDiary(@RequestBody DiariesDto.DiaryRequestDto diaryRequestDto) {
        DiariesDto.DiaryResponseDto response = diaryService.saveDiary(diaryRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/api/v1/diaries/{diary_id}")
    public ResponseEntity updateDiary(@PathVariable Long diaryId,
                                      @RequestBody DiariesDto.DiaryRequestDto diaryRequestDto) {
        DiariesDto.DiaryResponseDto response = diaryService.updateDiary(diaryId, diaryRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/api/v1/diaries/{diary_id}")
    public ResponseEntity findByDiary(@PathVariable Long diaryId) {
        DiariesDto.DiaryResponseDto response = diaryService.findById(diaryId);
        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/api/v1/diaries")
    public ResponseEntity findAllDiary() {
        List<DiariesDto.DiaryResponseDto> response = diaryService.findAll();
        return new ResponseEntity<>(new MultiResponseDto<>(response), HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/diaries/{diary_id}")
    public ResponseEntity deleteDiary(@PathVariable Long diaryId) {
        diaryService.deleteDiary(diaryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
