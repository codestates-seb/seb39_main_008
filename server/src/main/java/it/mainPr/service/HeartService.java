package it.mainPr.service;

import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.model.Diary;
import it.mainPr.model.Heart;
import it.mainPr.model.Member;
import it.mainPr.repository.DiaryRepository;
import it.mainPr.repository.HeartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class HeartService {
    private final HeartRepository heartRepository;
    private final DiaryRepository diaryRepository;
    private final MemberService memberService;

    public HeartService(HeartRepository heartRepository, DiaryRepository diaryRepository, MemberService memberService) {
        this.heartRepository = heartRepository;
        this.diaryRepository = diaryRepository;
        this.memberService = memberService;
    }

    public boolean createHeart(MemberResponseDto member, long diaryId) {
        //다이어리 존재 확인
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();

        //중복 좋아요 방지
//        if(isNotAlreadyHeart(member, diary)) {
//            heartRepository.save(new Heart(member, diary));
//            return true;
//        }
        return false;/**/
    }

    public List<String> count(long diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        MemberResponseDto member = MemberResponseDto.of(diary.getMember());

        Integer heartCount = heartRepository.countByDiary(diary).orElse(0);

        List<String> result =
                new ArrayList<>(Arrays.asList(String.valueOf(heartCount)));

        if(Objects.nonNull(member.getMemberId())) {
            result.add(String.valueOf(isNotAlreadyHeart(member, diary)));
            return result;
        }
        return result;
    }

    public void deleteHeart(MemberResponseDto member, long diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        Heart heart = heartRepository.findByMemberAndDiary(member, diary).orElseThrow();

        heartRepository.delete(heart);
    }
    //좋아요 중복 체크
    private boolean isNotAlreadyHeart(MemberResponseDto member, Diary diary) {
        return heartRepository.findByMemberAndDiary(member, diary).isEmpty();
    }

    public List<Heart> findMemberHeart(Member member) {
        return heartRepository.findByMember(member);
    }
}
