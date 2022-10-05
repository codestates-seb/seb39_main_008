package it.mainPr.mapper;

import it.mainPr.dto.diaryDto.DiariesDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.model.Diary;
import it.mainPr.model.Heart;
import it.mainPr.model.Member;
import it.mainPr.service.DiaryService;
import it.mainPr.service.HeartService;
import it.mainPr.service.MemberService;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiaryMapper {
    Diary postDtoToDiary(DiariesDto.PostDto postDto);
    DiariesDto.DiaryResponseDto diaryToResponseDto(Diary diary);
    List<DiariesDto.DiaryResponseDto> diaryListToResponseDtoList(List<Diary> diaries);

    default  Diary diaryPostDtoToDiary(MemberService memberService, DiariesDto.PostDto postDto) {
        Diary diary = new Diary();
        diary.setCategory(postDto.getCategory());
        diary.setDiary_title(postDto.getDiary_title());
        diary.setDiary_subtitle(postDto.getDiary_subtitle());
        diary.setContent(postDto.getContent());

//        if(postDto.getStoreImages()==null){
//            System.out.printf("이미지 아무것도 안들어옴");
//        }else{//이미지 한개 이상 들어왔을 때 -> 해당 이미지들을 Store객체에 넣어준다.
//            List<StoreImage> diaryImages = diaryImageDtosToStoreImages(diaryPostDto.getStoreImages(),diary);
//            diary.setStoreImages(diaryImages);
//        }

        Member member = memberService.getLoginMember();// 현재 로그인된 유저 가져옴
        diary.setMember(member);

        return diary;
    }

//    default  List<StoreImage> diaryImageDtosToStoreImages(List<StoreImageDto> diaryImageDtos,Store diary){
//        // changing from diaryImageDtos to StoreImages
//        return diaryImageDtos.stream().map(diaryImageDto -> {
//            StoreImage diaryImage = new StoreImage();
//            diaryImage.setStore(diary);
//            diaryImage.setImage(diaryImageDto.getStoreImage());
//            return diaryImage;
//        }).collect(Collectors.toList());
//    }

//    default  List<StoreImageResponseDto> diaryImagesToStoreImageResponseDtos(List<StoreImage> diaryImages){
//
//        return diaryImages.stream()
//                .map(diaryImage -> {
//                    StoreImageResponseDto diaryImageResponseDto =  new StoreImageResponseDto();
//                    diaryImageResponseDto.setStoreImage(diaryImage.getImage());
//                    diaryImageResponseDto.setStoreImageStatus(diaryImage.getStoreImageStatus());
//                    return  diaryImageResponseDto;
//                }).collect(Collectors.toList());
//    }

    default DiariesDto.DiaryResponseDto diaryToStoreAndReviewResponseDto(DiaryService diaryService, HeartService heartService, DiaryMapper diaryMapper, MemberMapper memberMapper,
                                                                         Diary diary, Integer diaryPage, Integer diarySize, String diarySort){

        DiariesDto.DiaryResponseDto diaryAndReviewResponseDto = new DiariesDto.DiaryResponseDto();
        diaryAndReviewResponseDto.setDiaryId(diary.getDiaryId());
//        diaryAndReviewResponseDto.setCreatedAt(diary.getCreatedAt());
//        diaryAndReviewResponseDto.setUpdatedAt(diary.getUpdatedAt());
        diaryAndReviewResponseDto.setDiaryStatus(diary.getDiaryStatus());
        diaryAndReviewResponseDto.setCategory(diary.getCategory());
        diaryAndReviewResponseDto.setDiary_title(diary.getDiary_title());
        diaryAndReviewResponseDto.setDiary_subtitle(diary.getDiary_subtitle());
        diaryAndReviewResponseDto.setContent(diary.getContent());

        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponse(diary.getMember());
        diaryAndReviewResponseDto.setMember(memberResponseDto);

//        diaryAndReviewResponseDto.setStoreImages(diaryImagesToStoreImageResponseDtos(//가게에 대한 이미지 속성 추가
//                diaryImageService.findVerifiedStoreImages(diary)//해당 스토어의 스토어이미지들 중에서 status가 STORE_IMAGE_EXIST것만 반환
//        ));

        List<Heart> hearts = heartService.findExistHeartsByDiary(diary);//해당 diary의 하트중에 Status가 HEART_EXIST인 하트들만 반환
        List<Long> heartMemberId = hearts.stream().map(heart -> heart.getMember().getMemberId()).collect(Collectors.toList());
        diaryAndReviewResponseDto.setHeartMemberId(heartMemberId);

        return diaryAndReviewResponseDto;
    }

    default DiariesDto.DiaryResponseDto diaryToDiaryResponseDto(DiaryService diaryService,HeartService heartService,DiaryMapper diaryMapper, MemberMapper memberMapper, Diary diary){
        DiariesDto.DiaryResponseDto diaryResponseDto = new DiariesDto.DiaryResponseDto();
        diaryResponseDto.setDiaryId(diary.getDiaryId());
        diaryResponseDto.setDiaryStatus(diary.getDiaryStatus());
        diaryResponseDto.setCategory(diary.getCategory());
        diaryResponseDto.setDiary_title(diary.getDiary_title());
        diaryResponseDto.setDiary_subtitle(diary.getDiary_subtitle());
        diaryResponseDto.setContent(diary.getContent());

        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponse(diary.getMember());
        diaryResponseDto.setMember(memberResponseDto);

//        diaryResponseDto.setStoreImages(diaryImagesToStoreImageResponseDtos(//가게에 대한 이미지 속성 추가
//                diaryImageService.findVerifiedStoreImages(diary)//해당 스토어의 스토어이미지들 중에서 status가 STORE_IMAGE_EXIST것만 반환
//        ));

//        List<ReviewResponseDto> reviewResponseDtos = reviewMapper.reviewsToExistReviewResponseDtos(reviewService,userMapper,diary.getReviews());
//        diaryResponseDto.setReviews(reviewResponseDtos);


        List<Heart> hearts = heartService.findExistHeartsByDiary(diary);//해당 diary의 하트중에 Status가 HEART_EXIST인 하트들만 반환
        List<Long> heartMemberId = hearts.stream().map(heart -> heart.getMember().getMemberId()).collect(Collectors.toList());
        diaryResponseDto.setHeartMemberId(heartMemberId);


        return diaryResponseDto;
    }

    default List<DiariesDto.DiaryResponseDto> diarysToDiaryResponseDtos(DiaryService diaryService,HeartService heartService,DiaryMapper diaryMapper, MemberMapper memberMapper, List<Diary> diaries){
        return diaries.stream().map(diary -> diaryToDiaryResponseDto(diaryService,heartService,diaryMapper,memberMapper,diary))
                .collect(Collectors.toList());
    };

    default Diary diaryPatchDtoToDiary(DiaryService diaryService,MemberService memberService, long diaryId, DiariesDto.PatchDto patchDto) {

        if(memberService.getLoginMember() != diaryService.findMemberAtDiary(diaryId)){
            //접근 오너가 가지고 있는 가게가 아니므로 수정 삭제 불가
            throw new BusinessLogicalException(ExceptionCode.ACCESS_DENIED_MEMBER);
        }
        Diary diary = new Diary();
        diary.setDiaryId(diaryId);

        diary.setMember(memberService.getLoginMember());

        // changing from StoreImageDto to StoreImage
//        if(diaryPatchDto.getStoreImages()==null){
//            System.out.printf("이미지 아무것도 안들어옴!!");
//        }else{//이미지 한개 이상 들어왔을 때 -> 해당 이미지들을 Store객체에 넣어준다.
//            List<StoreImage> diaryImages = diaryImageDtosToStoreImages(diaryPatchDto.getStoreImages(),diary);
//            diary.setStoreImages(diaryImages);
//        }


        diary.setCategory(patchDto.getCategory());
        diary.setDiaryId(patchDto.getDiaryId());
        diary.setDiaryStatus(patchDto.getDiaryStatus());
        diary.setCategory(patchDto.getCategory());
        diary.setDiary_title(patchDto.getDiary_title());
        diary.setDiary_subtitle(patchDto.getDiary_subtitle());
        diary.setContent(patchDto.getContent());

        //스토어 삭제
        diary.setDiaryStatus(patchDto.getDiaryStatus());
        System.out.println(diary.getDiaryStatus());

        return diary;

    }
}
