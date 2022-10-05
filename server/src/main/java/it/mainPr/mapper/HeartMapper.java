package it.mainPr.mapper;

import it.mainPr.dto.diaryDto.DiariesDto;
import it.mainPr.dto.heartDto.HeartPatchDto;
import it.mainPr.dto.heartDto.HeartPostDto;
import it.mainPr.dto.heartDto.HeartResponseDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.model.Diary;
import it.mainPr.model.Heart;
import it.mainPr.model.Member;
import it.mainPr.service.DiaryService;
import it.mainPr.service.HeartService;
import it.mainPr.service.MemberService;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface HeartMapper {

    default Heart heartPostDtoToHeart(DiaryService diaryService, MemberService memberService, HeartPostDto heartPostDto) {

        Member member = memberService.getLoginMember(); // request http 헤더의 토큰에 해당하는 유저 불러옴
        Diary diary = diaryService.findVerifiedDiary(heartPostDto.getDiaryId()); //유저가 하트누른 가게 불러오기
        Heart heart = new Heart();

        heart.setDiary(diary);
        heart.setMember(member);

        return heart;

    }
//
//
    default Heart heartPatchDtoToHeart(DiaryService diaryService, MemberService memberService, HeartPatchDto heartPatchDto) {

        Member member = memberService.getLoginMember(); // request http 헤더의 토큰에 해당하는 유저 불러옴
        Diary diary = diaryService.findVerifiedDiary(heartPatchDto.getDiaryId()); //유저가 하트누른 가게 불러오기
        Heart heart = new Heart();

        heart.setHeartStatus(heartPatchDto.getHeartStatus());
        heart.setDiary(diary);
        heart.setMember(member);


        return heart;
    }

//    default HeartResponseDto heartToHeartResponseDto(DiaryService diaryService, HeartService heartService, DiaryMapper diaryMapper, MemberMapper memberMapper, Heart heart) {
//        HeartResponseDto heartResponseDto = new HeartResponseDto();
//        heartResponseDto.setHeartId(heart.getHeartId());
//        heartResponseDto.setHeartStatus(heart.getHeartStatus());
//        heartResponseDto.setModifiedAt(heart.getModifiedAt());
//        heartResponseDto.setCreatedAt(heart.getCreatedAt());
//
//        DiariesDto.DiaryResponseDto diaryResponseDto = diaryMapper.diaryToDiaryResponseDto(diaryService, heartService, diaryMapper, memberMapper, heart.getDiary());
//        heartResponseDto.setDiary(diaryResponseDto);
//
//        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponse(heart.getMember());
//        heartResponseDto.setMember(memberResponseDto);
//
//
//
//        return heartResponseDto;
//    }

//    default List<HeartResponseDto> heartsToHeartResponseDtos(DiaryService diaryService, HeartService heartService,DiaryMapper diaryMapper,MemberMapper memberMapper,List<Heart> hearts) {
//        return hearts.stream().map(heart -> heartToHeartResponseDto(diaryService,heartService,diaryMapper,memberMapper,heart))
//                .collect(Collectors.toList());
//    };
}
