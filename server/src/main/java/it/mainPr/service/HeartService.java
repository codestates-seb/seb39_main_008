package it.mainPr.service;

import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.model.Diary;
import it.mainPr.model.Heart;
import it.mainPr.model.Member;
import it.mainPr.repository.DiaryRepository;
import it.mainPr.repository.HeartRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;

    @Transactional
    public Heart createHeart(Heart heart){

        verifyExistHeart(heart.getMember(),heart.getDiary());//이미 등록된 하트인지 확인

        return heartRepository.save(heart);
    }

    @Transactional
    public Heart updateHeart(Heart heart){

        Heart findHeart = findExistHeart(heart.getMember(),heart.getDiary());//등록된 하트면 반환->등록된 하트가 아니면 에러 발생

        Optional.ofNullable(heart.getHeartStatus())//하트 삭제
                .ifPresent(heartStatus -> findHeart.setHeartStatus(heartStatus));

        return findHeart;
    }

    public Page<Heart> findHearts(MemberService memberService, int page, int size){//해당 유저가 누른 하트에 pagenation과 최신순 sort 구현
        Member member = memberService.getLoginMember(); //해당토큰의 유저 가져오기
        Page<Heart> hearts = heartRepository.findByMemberAndHeartStatus(//삭제된 하트 빼고 해당 유저의 전체 하트 가져옴
                PageRequest.of(page,size, Sort.by("createdAt").descending()),
                member,
                Heart.HeartStatus.HEART_EXIST);
        verifiedNoHeart(hearts);//findAllHeart안의 반환된 데이터가 없으면 예외발생

        return hearts;
    }

    private void verifiedNoHeart(Page<Heart> hearts){
        if(hearts.getTotalElements()==0){
            throw new BusinessLogicalException(ExceptionCode.HEART_NOT_FOUND);
        }
    }

    private Heart findExistHeart(Member member, Diary diary){//등록된 하트면 반환->등록된 하트가 아니면 에러!

        Optional<Heart> heart = heartRepository.findByMemberAndDiaryAndHeartStatus(member,diary, Heart.HeartStatus.HEART_EXIST);
        if(!heart.isPresent()) //등록된 하트가 아니면 에러!
            throw new BusinessLogicalException(ExceptionCode.HEART_NOT_FOUND);

        return heart.get();
    }

    private void verifyExistHeart(Member member, Diary diary){//이미 등록된 하트인지 확인

        Optional<Heart> heart = heartRepository.findByMemberAndDiaryAndHeartStatus(member,diary, Heart.HeartStatus.HEART_EXIST);
        if(heart.isPresent()) //이미 등록된 하트면 예외처리!
            throw new BusinessLogicalException(ExceptionCode.HEART_EXIST);

    }
    public List<Heart> findExistHeartsByDiary(Diary diary){ //해당 store의 하트중에 Status가 HEART_EXIST인 하트들만 반환
        return heartRepository.findByDiaryAndHeartStatus(diary, Heart.HeartStatus.HEART_EXIST);
    }

}
