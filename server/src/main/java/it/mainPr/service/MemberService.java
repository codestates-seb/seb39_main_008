package it.mainPr.service;

import it.mainPr.auth.utils.CustomAuthorityUtils;
import it.mainPr.auth.utils.SecurityUtils;
import it.mainPr.dto.MemberPatchDto;
import it.mainPr.dto.MemberPostDto;
import it.mainPr.dto.MemberResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;

import it.mainPr.mapper.MemberMapper;
import it.mainPr.model.Member;
import it.mainPr.repository.MemberRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;
    private final CustomAuthorityUtils authorityUtils;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, MemberMapper memberMapper, CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberMapper = memberMapper;
        this.authorityUtils = authorityUtils;
    }

    //회원 가입
    public MemberResponseDto createMember(MemberPostDto postDto) {
        verifyExistsEmail(postDto.getEmail());

        Member newMember = Member.builder()
                .email(postDto.getEmail())
                .password(passwordEncoder.encode(postDto.getPassword()))
                .name(postDto.getName())
                .nickname(postDto.getNickname())
                .build();

        List<String> roles = authorityUtils.createRoles(newMember.getEmail());
        newMember.setRoles(roles);

        Member createdMember = memberRepository.save(newMember);
        return MemberResponseDto.of(createdMember);
    }

    public MemberResponseDto loginMember(Authentication authentication){
        Member member = (Member) authentication.getPrincipal();
        Member loginMember = memberRepository.findByEmail(member.getEmail())
                .orElseThrow(()->new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));
        return memberMapper.memberToMemberResponse(loginMember);
    }

    //회원 정보 수정
    public MemberResponseDto updateMember(MemberPatchDto patchDto) {
        Long memberId = SecurityUtils.getCurrentMemberId();
        Member findMember = findVerifiedMember(memberId);

        Optional.ofNullable(patchDto.getInformation())
                .ifPresent(information -> findMember.setInformation(information));
        Optional.ofNullable(patchDto.getImgUrl())
                .ifPresent(imgUrl -> findMember.setImgUrl(imgUrl));

        return MemberResponseDto.of(memberRepository.save(findMember));
    }
    //특정 회원 찾기
    @Transactional(readOnly = true)
    public MemberResponseDto findMember(long memberId) {

        return memberRepository.findById(memberId)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));
    }
    //회원 리스트 보기
    public List<MemberResponseDto> findAllMembers() {
        return memberRepository.findAll().stream()
                .map(member -> MemberResponseDto.of(member))
                .collect(Collectors.toList());
    }
    //회원 탈퇴
    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        memberRepository.delete(findMember);
    }

    @Transactional(readOnly = true)
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(() ->
                        new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicalException(ExceptionCode.MEMBER_EXISTS);
    }
}