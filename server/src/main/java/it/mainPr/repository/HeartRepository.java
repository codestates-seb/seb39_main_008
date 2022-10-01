package it.mainPr.repository;

import it.mainPr.dto.MemberResponseDto;
import it.mainPr.model.Diary;
import it.mainPr.model.Heart;
import it.mainPr.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart,Long> {

    Optional<Integer> countByDiary(Diary diary);
    Optional<Heart> findByMemberAndDiary(MemberResponseDto member, Diary diary);

    List<Heart> findByMember(Member member);

}
