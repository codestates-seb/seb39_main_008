package it.mainPr.auth.filter;



import com.fasterxml.jackson.databind.ObjectMapper;
import it.mainPr.auth.jwt.JwtTokenizer;
import it.mainPr.auth.userDetails.MemberDetails;
import it.mainPr.dto.memberDto.LoginDto;
import it.mainPr.model.Member;
import it.mainPr.repository.MemberRepository;
import it.mainPr.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = null;
        try {
            objectMapper.readValue(request.getInputStream(), LoginDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("JwtAuthenticationFilter : " + loginDto);

        memberService.verifyExistMemberByEmailAndOriginal(loginDto.getEmail());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        log.info("JwtAuthenticationFilter: 토큰 생성 완료");

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        log.info("Authentication : " + memberDetails.getMember().getEmail());

        return authentication;
    }

    @Override
    @Transactional
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws ServletException, IOException {

        MemberDetails memberDetails = (MemberDetails) authResult.getPrincipal();

        String accessToken = delegateAccessToken(memberDetails);
        String refreshToken = delegateRefreshToken(memberDetails);

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh", "Bearer " + refreshToken);
    }
//
//        Map<String, String> tokens = new HashMap<>();
//        tokens.put("Authorization", "Bearer " + accessToken);
//        tokens.put("Refresh", "Bearer " + accessToken);
//        response.setContentType(APPLICATION_JSON_VALUE);
//
//        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//
//        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
//}

    private String delegateAccessToken(MemberDetails memberDetails) {
        Map<String, Object> claims = new HashMap<>();
        Member member = memberDetails.getMember();
        claims.put("username", member.getEmail());
        claims.put("roles", member.getRole());

        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(MemberDetails memberDetails) {
        Map<String, Object> claims = new HashMap<>();
        Member member = memberDetails.getMember();
        claims.put("username", member.getEmail());
        claims.put("roles", member.getRole());

        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(claims, subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}