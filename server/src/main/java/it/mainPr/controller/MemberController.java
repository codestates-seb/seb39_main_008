package it.mainPr.controller;

import it.mainPr.dto.AccessTokenDto;
import it.mainPr.dto.RefreshTokenDto;
import it.mainPr.dto.global.SingleResponseDto;
import it.mainPr.dto.memberDto.MemberPatchDto;
import it.mainPr.dto.memberDto.MemberPostDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.mapper.MemberMapper;
import it.mainPr.model.Member;
import it.mainPr.service.BookService;
import it.mainPr.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
@Validated
@AllArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final BookService bookService;


    @PostMapping("/v1/auth/signup")
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto postDto) {
        Member member = memberMapper.memberPostToMember(postDto);
        Member createdMember = memberService.createMember(member);

        return new ResponseEntity<>(new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(createdMember)),HttpStatus.CREATED);
    }

    @PatchMapping("/v1/members/{member_id}")
    public ResponseEntity patchMember(@RequestParam(name = "member_id") long memberId,
                                      @Valid @RequestBody MemberPatchDto requestBody) {
        Member member = memberMapper.memberPatchToMember(requestBody);
        Member updatedMember = memberService.updateMember(member, requestBody);

        return new ResponseEntity<>(new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(updatedMember)), HttpStatus.OK);
    }

    @GetMapping("/v1/members/{memberId}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable long memberId) {
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(System.out::println);
        return ResponseEntity.ok(memberService.findMember(memberId));
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

//    @GetMapping("/v1/auth/reissue")
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        memberService.reissue(request, response);
//    }

    @PostMapping("/token-refresh")
    public ResponseEntity getToken(@RequestBody RefreshTokenDto refreshToken) {
        log.info("Start getToken Controller");

        String accessToken = memberService.createAccessToken(refreshToken.getRefreshToken());

        AccessTokenDto accessTokenDto = new AccessTokenDto();

        accessTokenDto.setAccessToken(accessToken);

        return new ResponseEntity<>(new SingleResponseDto<>(accessTokenDto),HttpStatus.OK);
    }
}