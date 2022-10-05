package it.mainPr.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import it.mainPr.auth.jwt.JwtTokenizer;
import it.mainPr.auth.utils.CustomAuthorityUtils;
import it.mainPr.auth.utils.SecurityUtils;
import it.mainPr.dto.memberDto.MemberPatchDto;
import it.mainPr.dto.memberDto.MemberPostDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.mapper.MemberMapper;
import it.mainPr.model.Member;
import it.mainPr.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;
    private final JwtTokenizer jwtTokenizer;

    //회원 가입
    public MemberResponseDto createMember(MemberPostDto postDto) {
        verifyExistsEmail(postDto.getEmail());

        Member newMember = Member.builder()
                .email(postDto.getEmail())
                .password(passwordEncoder.encode(postDto.getPassword()))
                .name(postDto.getName())
                .nickname(postDto.getNickname())
                .imgUrl("https://mblogthumb-phinf.pstatic.net/MjAyMDA2MTBfMTY1/MDAxNTkxNzQ2ODcyOTI2.Yw5WjjU3IuItPtqbegrIBJr3TSDMd_OPhQ2Nw-0-0ksg.8WgVjtB0fy0RCv0XhhUOOWt90Kz_394Zzb6xPjG6I8gg.PNG.lamute/user.png?type=w800")
                .build();

        List<String> roles = authorityUtils.createRoles(newMember.getEmail());
        newMember.setRoles(roles);

        Member createdMember = memberRepository.save(newMember);
        return MemberResponseDto.of(createdMember);
    }

    public void reissue(HttpServletRequest request, HttpServletResponse response) {
        //리프레시토큰 검증
        String refreshToken = request.getHeader("Authorization");

        if(refreshToken != null && refreshToken.startsWith("Bearer ")) {
            try {
                String authorizationHeader = refreshToken.replace("Bearer ", "");
                String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
                Map<String, Object> verifiedClaims = jwtTokenizer.getClaims(authorizationHeader, base64EncodedSecretKey).getBody();

                String username = (String) verifiedClaims.get("username");
                Member member = findVerifiedMember(username);

                //access토큰 재발급
                Map<String, Object> claims = new HashMap<>();
                claims.put("username", member.getEmail());
                claims.put("roles", member.getRoles());
                String subject = member.getEmail();
                Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
                String secretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
                String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, secretKey);

                response.setHeader("Authorization", "Bearer " + accessToken);
                response.setHeader("Refresh", refreshToken);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("Authorization", "Bearer " + accessToken);
                tokens.put("Refresh", refreshToken);

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (SignatureException se) {
                request.setAttribute("exception", se);
            } catch (ExpiredJwtException ee) {
                request.setAttribute("exception", ee);
            } catch (Exception e) {
                request.setAttribute("exception", e);
            }
        }
    }

    //회원 정보 수정
    public MemberResponseDto updateMember(MemberPatchDto patchDto) {
        String memberEmail = SecurityUtils.getCurrentMemberEmail();
        Member findMember = findVerifiedMember(memberEmail);

        Optional.ofNullable(patchDto.getInformation())
                .ifPresent(information -> findMember.setInformation(information));
        Optional.ofNullable(patchDto.getImgUrl())
                .ifPresent(imgUrl -> findMember.setImgUrl(imgUrl));

        return MemberResponseDto.of(memberRepository.save(findMember));
    }
    //특정 회원 찾기
    @Transactional(readOnly = true)
    public MemberResponseDto findMember(long memberId) {

        return memberRepository.findByMemberId(memberId)
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

    @Transactional(readOnly = true)
    public Member findVerifiedMember(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member findMember = optionalMember.orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicalException(ExceptionCode.MEMBER_EXISTS);
    }

    public Member getLoginMember(){ //로그인된 유저가 옳바른 지 확인하고 정보 가져옴
        return findVerifiedMember(SecurityUtils.getCurrentMemberEmail());
    }

    private Member findMember(Member member){// 아래 getUserByToken 쓸거임
        return findVerifiedMember(member.getMemberId());
    }

}