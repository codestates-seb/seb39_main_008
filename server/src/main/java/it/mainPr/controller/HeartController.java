package it.mainPr.controller;

import it.mainPr.dto.global.MultiResponseDto;
import it.mainPr.dto.global.SingleResponseDto;
import it.mainPr.dto.heartDto.HeartPatchDto;
import it.mainPr.dto.heartDto.HeartPostDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.mapper.CommentMapper;
import it.mainPr.mapper.DiaryMapper;
import it.mainPr.mapper.HeartMapper;
import it.mainPr.mapper.MemberMapper;
import it.mainPr.model.Heart;
import it.mainPr.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Objects;

@RestController
@Validated
@Slf4j
@AllArgsConstructor
public class HeartController {
    private final HeartService heartService;
    private final HeartMapper heartMapper;
    private final HeartMapper mapper;
    private final MemberService memberService;
    private final DiaryService diaryService;
    private final DiaryMapper diaryMapper;
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final MemberMapper memberMapper;
    private final DiaryImageService diaryImageService;


    /**
     * Diary 하트등록 API
     * **/
    @PostMapping("/api/v1/member/heart/register")
    public ResponseEntity postHeart(@Valid @RequestBody HeartPostDto heartPostDto){
        Heart heart = mapper.heartPostDtoToHeart(diaryService,memberService,heartPostDto);
        Heart createdHeart = heartService.createHeart(heart);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.heartToHeartResponseDto(
                        commentService,heartService,commentMapper,diaryMapper,memberMapper, diaryImageService, createdHeart)),
                HttpStatus.CREATED
        );
    }

    /**
     * Diary 하트등록 취소 API
     * **/
    @PatchMapping("/api/v1/heart/cancel")
    public ResponseEntity patchHeart(@Valid @RequestBody HeartPatchDto heartPatchDto){

        Heart heart = mapper.heartPatchDtoToHeart(diaryService,memberService,heartPatchDto);

        Heart updatedHeart = heartService.updateHeart(heart);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.heartToHeartResponseDto(
                        commentService,heartService,commentMapper,diaryMapper,memberMapper, diaryImageService, updatedHeart)),
                HttpStatus.OK
        );

    }

    /**
     * 하트 누른 목록리스트 가져오기API
     * **/
    @GetMapping("/api/v1/heart/list")
    public ResponseEntity getHearts(@Positive @RequestParam("page") int page,
                                    @Positive @RequestParam("size") int size){

        Page<Heart> pageHearts = heartService.findHearts(memberService,page-1,size);

        List<Heart> hearts = pageHearts.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(heartMapper.heartToHeartResponseDtos(
                commentService, heartService, commentMapper, diaryMapper, memberMapper, diaryImageService, hearts),
                pageHearts),HttpStatus.OK);
    }





}