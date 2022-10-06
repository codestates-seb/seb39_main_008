package it.mainPr.auth.userDetails;

import it.mainPr.auth.utils.CustomAuthorityUtils;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.model.Member;
import it.mainPr.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
//    private final CustomAuthorityUtils authorityUtils;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("MemberServiceDetails : 진입");
        Optional<Member> memberEntity = memberRepository.findByEmailAndMemberStatus(email, Member.MemberStatus.MEMBER_EXIST);

        return new MemberDetails(memberEntity.get());
    }

}