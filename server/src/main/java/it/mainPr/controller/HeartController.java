package it.mainPr.controller;

import it.mainPr.dto.HeartDto;
import it.mainPr.dto.MemberResponseDto;
import it.mainPr.mapper.HeartMapper;
import it.mainPr.service.HeartService;
import it.mainPr.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@RestController
@Validated
public class HeartController {
    private final HeartService heartService;
    private final HeartMapper mapper;
    private final MemberService memberService;

    public HeartController(HeartService heartService, HeartMapper mapper, MemberService memberService) {
        this.heartService = heartService;
        this.mapper = mapper;
        this.memberService = memberService;
    }

    @PostMapping("/api/v1/heart/{diary_id}")
    public ResponseEntity createdHeart(@PathVariable("diary_id") long diaryId,
                                       @Valid @RequestBody HeartDto heartDto) {
        boolean result = false;

        MemberResponseDto member = memberService.findMember(heartDto.getMemberId());

        if(Objects.nonNull(member))
            result = heartService.createHeart(member, diaryId);

        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/api/v1/heart/delete/{diary_id}")
    public ResponseEntity deleteHeart(@PathVariable("diary_id") long diaryId,
                                      HeartDto heartDto) {
        MemberResponseDto member = memberService.findMember(heartDto.getMemberId());
        if(member != null) {
            heartService.deleteHeart(member, diaryId);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
