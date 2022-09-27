package it.mainPr.controller;

import it.mainPr.dto.member.MemberPatchDto;
import it.mainPr.dto.member.MemberPostDto;
import it.mainPr.dto.member.MemberResponseDto;
import it.mainPr.service.MemberService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
@Validated
@Slf4j
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/v1/auth/signup")
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto requestBody) {

        return ResponseEntity.ok(memberService.createMember(requestBody));
    }

    @PatchMapping("/v1/members")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId,
                                      @Valid @RequestBody MemberPatchDto requestBody) {

        return ResponseEntity.ok(memberService.updateMember(requestBody));
    }

    @GetMapping("/v1/members/{member-id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable("member-id") long memberId) {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(System.out::println);
        return ResponseEntity.ok(memberService.findMember(memberId));
    }

    @GetMapping("/v1/members")
    public ResponseEntity<List<MemberResponseDto>> findAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @DeleteMapping("/v1/members/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId) {

        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}