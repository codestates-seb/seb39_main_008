package it.mainPr.repository;

import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.model.Diary;
import it.mainPr.model.Heart;
import it.mainPr.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart,Long> {


    Optional<Heart> findByMemberAndDiaryAndHeartStatus(Member member, Diary diary, Heart.HeartStatus heartStatus);

    Page<Heart> findByMemberAndHeartStatus(Pageable pageable, Member member, Heart.HeartStatus heartStatus);

    List<Heart> findByDiaryAndHeartStatus(Diary diary,Heart.HeartStatus heartStatus);
}

