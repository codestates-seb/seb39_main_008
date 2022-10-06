package it.mainPr.controller;

import it.mainPr.dto.memberDto.MemberPatchDto;
import it.mainPr.dto.memberDto.MemberPostDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.model.Member;
import it.mainPr.service.MemberService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
@Validated
@CrossOrigin(origins = "*")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/v1/auth/signup")
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto requestBody) {

        return ResponseEntity.ok(memberService.createMember(requestBody));
    }

    @PatchMapping("/v1/members")
    public ResponseEntity patchMember(@RequestParam(name = "member-id") long memberId,
                                      @Valid @RequestBody MemberPatchDto requestBody) {

        return ResponseEntity.ok(memberService.updateMember(requestBody));
    }

    @GetMapping("/v1/members/{memberId}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable long memberId) {

        return ResponseEntity.ok(memberService.findMember(memberId));
    }

    @GetMapping("/v1/members/current")
    public ResponseEntity<MemberResponseDto> currentMember() {

        return ResponseEntity.ok(memberService.currentMember());
    }

    @GetMapping("/v1/members")
    public ResponseEntity<List<MemberResponseDto>> findAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @DeleteMapping("/v1/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable long memberId) {

        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/v1/auth/reissue")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        memberService.reissue(request, response);
    }

}