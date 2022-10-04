//package it.mainPr.auth.jwt;
//
//import it.mainPr.model.Member;
//import it.mainPr.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class PrincipalDetailsService implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        System.out.println("PrincipalDetailsService : 진입");
//        Optional<Member> memberEntity = memberRepository.findByEmailAndMemberStatus(email, Member.MemberStatus.MEMBER_EXIST);
//
//
//        return new PrincipalDetails(memberEntity.get());
//    }
//}