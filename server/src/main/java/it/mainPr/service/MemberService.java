package it.mainPr.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import it.mainPr.auth.jwt.JwtTokenizer;
import it.mainPr.auth.userDetails.MemberDetails;
import it.mainPr.auth.utils.SecurityUtils;
import it.mainPr.dto.memberDto.MemberPatchDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.model.Member;
import it.mainPr.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@Slf4j
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;

    //회원 가입
    public Member createMember(Member member) {
        verifyExistsEmailByOriginal(member.getEmail());

        member.setPassword(passwordEncoder.encode(member.getPassword()));

        return memberRepository.save(member);
    }

//    public void reissue(HttpServletRequest request, HttpServletResponse response) {
//        //리프레시토큰 검증
//        String refreshToken = request.getHeader("Authorization");
//
//        if(refreshToken != null && refreshToken.startsWith("Bearer ")) {
//            try {
//                String authorizationHeader = refreshToken.replace("Bearer ", "");
//                String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
//                Map<String, Object> verifiedClaims = jwtTokenizer.getClaims(authorizationHeader, base64EncodedSecretKey).getBody();
//
//                String username = (String) verifiedClaims.get("username");
//                Member member = findVerifiedMember(username);
//
//                //access토큰 재발급
//                Map<String, Object> claims = new HashMap<>();
//                claims.put("username", member.getEmail());
//                claims.put("roles", member.getRole());
//                String subject = member.getEmail();
//                Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
//                String secretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
//                String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, secretKey);
//
//                response.setHeader("Authorization", "Bearer " + accessToken);
//                response.setHeader("Refresh", refreshToken);
//
//                Map<String, String> tokens = new HashMap<>();
//                tokens.put("Authorization", "Bearer " + accessToken);
//                tokens.put("Refresh", refreshToken);
//
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//
//            } catch (SignatureException se) {
//                request.setAttribute("exception", se);
//            } catch (ExpiredJwtException ee) {
//                request.setAttribute("exception", ee);
//            } catch (Exception e) {
//                request.setAttribute("exception", e);
//            }
//        }
//    }

    //회원 정보 수정
    public Member updateMember(Member member, MemberPatchDto patchDto) {
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getModifiedAt())
                        .ifPresent(memberModifiedAt -> findMember.setModifiedAt(memberModifiedAt));

        Optional.ofNullable(patchDto.getInformation())
                .ifPresent(information -> findMember.setInformation(information));
        Optional.ofNullable(patchDto.getImgUrl())
                .ifPresent(imgUrl -> findMember.setImgUrl(imgUrl));

        return findMember;
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

//    @Transactional(readOnly = true)
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
        return findMember(getMemberByToken());
    }

    private Member findMember(Member member){// 아래 getUserByToken 쓸거임
        return findVerifiedMember(member.getMemberId());
    }

    public Member getMemberByToken(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberDetails memberDetails = (MemberDetails) principal;

        return memberDetails.getMember();
    }

    private void verifyExistsEmailByOriginal(String email) { // 현재 활동중인 일반 회원가입으로 가입한 유저의 이미 등록된 이메일인지 확인
        Optional<Member> member = memberRepository.findByEmailAndMemberStatus(email, Member.MemberStatus.MEMBER_EXIST);
        if (member.isPresent()){
            throw new BusinessLogicalException(ExceptionCode.MEMBER_EXISTS);
        }
    }

    public void verifyExistMemberByEmailAndOriginal(String email) { //현재 활동중인 일반 회원가입으로 가입한 유저중 email 파라미터로 조회
        Optional<Member> member = memberRepository.findByEmailAndMemberStatus(email, Member.MemberStatus.MEMBER_EXIST);
        if (member.isEmpty()){//DB에 없는 유저거나 이전에 탈퇴한 유저면 예외처리함
            throw new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND);
        }
    }

    public String createAccessToken(String refreshToken) {
        Map<String, Object> claims = null;
        Member member = null;
        try {
            String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
            claims = jwtTokenizer.getClaims(refreshToken, base64EncodedSecretKey).getBody();
            System.out.println(claims);
            Long memberId = Long.parseLong(claims.get("memberId").toString());
            member = memberRepository.findById(memberId).get();


        } catch (SignatureException se) {
            throw new JwtException("사용자 인증 실패");

        } catch (ExpiredJwtException ee) {
            throw new JwtException("토큰 기한 만료");
        } catch (Exception e) {
            throw e;
        }//이걸 통과하면 리프레시 토큰이 DB의 리프레시 토큰과 같다면 어세스 토큰 갱신


        ////////////어세스 토큰 갱신 부분/////////////////////////////////////////////////////////////////
        String subject = member.getMemberId().toString(); //Jwt 의 제목
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        String accessToken = "Bearer " + jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        return accessToken;
    }
}