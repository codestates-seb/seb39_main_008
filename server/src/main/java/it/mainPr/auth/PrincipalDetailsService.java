//package it.mainPr.auth;
//
//import it.mainPr.exception.BusinessLogicalException;
//import it.mainPr.exception.ExceptionCode;
//import it.mainPr.model.Member;
//import it.mainPr.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class PrincipalDetailsService implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Member member = memberRepository.findByEmail(username)
//                .orElseThrow(() -> new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));
//        return new PrincipalDetails(member);
//    }
//}