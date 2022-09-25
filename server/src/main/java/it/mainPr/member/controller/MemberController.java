package it.mainPr.member.controller;
import it.mainPr.dto.MultiResponseDto;
import it.mainPr.dto.SingleResponseDto;
import it.mainPr.member.dto.MemberDto;
import it.mainPr.member.entity.Member;
import it.mainPr.member.mapper.MemberMapper;
import it.mainPr.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping
@Validated
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping("/v1/auth/signup")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {

        Member member = mapper.memberPostToMember(requestBody);

        Member createdMember = memberService.createMember(member);
        MemberDto.Response response = mapper.memberToMemberResponse(createdMember);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/v1/members")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId,
                                      @Valid @RequestBody MemberDto.Patch requestBody) {

        requestBody.setMemberId(memberId);

        Member member =
                memberService.updateMember(mapper.memberPatchToMember(requestBody));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponse(member)), HttpStatus.OK);
    }

    @GetMapping("/v1/members/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {

        Member member = memberService.findMember(memberId);

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberResponse(member)), HttpStatus.OK);
    }

    @GetMapping("v1/members")
    public ResponseEntity getMembers(@RequestParam int page,
                                     @RequestParam int size) {

        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
        List<Member> members = pageMembers.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.membersToMemberResponses(members), pageMembers), HttpStatus.OK);
    }

    @DeleteMapping("v1/members/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId) {

        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}