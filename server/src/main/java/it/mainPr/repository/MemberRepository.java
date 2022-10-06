package it.mainPr.repository;

import it.mainPr.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByMemberId(long memberId);

    Optional<Member> findByEmailAndMemberStatus(String email, Member.MemberStatus memberStatus);

}
