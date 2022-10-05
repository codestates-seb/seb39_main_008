package it.mainPr.mapper;

import it.mainPr.dto.commentDto.CommentResponseDto;
import it.mainPr.dto.diaryDto.DiariesDto;
import it.mainPr.dto.diaryDto.DiaryImageDto;
import it.mainPr.dto.diaryDto.DiaryImageResponseDto;
import it.mainPr.dto.global.MultiResponseDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.model.*;
import it.mainPr.service.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiaryMapper {

    default Diary diaryPostDtoToDiary(MemberService memberService, DiariesDto.PostDto postDto) {
        Diary diary = new Diary();


        // 하나의 diary에 1장 or 여러 장의 image를 올릴 수도 있고, image 업로드 없이 diary를 등록할 수도 있다.
        if(postDto.getDiaryImgUrl() == null) {
            System.out.printf("첨부된 사진이 없음");
        } else {
            List<DiaryImage> diaryImages = diaryImageDtosToDiaryImages(postDto.getDiaryImgUrl(), diary);
            diary.setDiaryImgUrl(diaryImages);
        }

        diary.setContent(postDto.getContent());
        diary.setMember(memberService.getLoginMember());

        return diary;
    }

    default List<DiaryImage> diaryImageDtosToDiaryImages(List<DiaryImageDto> diaryImageDtos, Diary diary) {
        return diaryImageDtos.stream().map(diaryImageDto -> {
            DiaryImage diaryImage = new DiaryImage();
            diaryImage.addDiary(diary); // diary가 생성되어야만 diaryImage를 업로드 할 수 있으니까?
            diaryImage.setImage(diaryImageDto.getDiaryImgUrl());
            return diaryImage;
        }).collect(Collectors.toList());
    }

    default Diary diaryPatchDtoToDiary(DiaryService diaryService, MemberService memberService, long diaryId, DiariesDto.PatchDto patchDto) {

        // 로그인 유저 = diary를 작성한 유저가 아니라면, 예외 처리. (수정 권한이 없기 때문에)
        if(memberService.getLoginMember().getMemberId() != diaryService.findDiaryMember(diaryId).getMemberId()) {
            throw new BusinessLogicalException(ExceptionCode.ACCESS_DENIED_MEMBER);
        }
        Diary diary = new Diary();
        diary.setDiaryId(diaryId);

        diary.setContent(patchDto.getContent()); // diary 본문 수정

        // changing from StoreImageDto to StoreImage
        if(patchDto.getDiaryImgUrl() == null){
            System.out.printf("이미지 아무것도 안들어왔습니다.");
        }else{//이미지 한개 이상 들어왔을 때
            List<DiaryImage> diaryImages = diaryImageDtosToDiaryImage(patchDto.getDiaryImgUrl(), diary);
            diary.setDiaryImgUrl(diaryImages);
        }
//        // diary 수정 시, 첨부된 이미지가 하나도 없다면 -> 빈 ArrayList 반환
//        if(diaryPatchDto.getDiaryImages() == null) {
//            diaryPatchDto.setDiaryImages(new ArrayList<>());
//        }
//        // diary 수정 시, 첨부된 이미지가 하나 이상 있다면 -> 해당 이미지를 등록
//        List<DiaryImage> diaryImages = diaryImageDtosToDiaryImage(diaryPatchDto.getDiaryImages(), diary);
//        diary.setDiaryImages(diaryImages);

        diary.setDiaryStatus(patchDto.getDiaryStatus());

        return diary;
    }
    default List<DiaryImage> diaryImageDtosToDiaryImage(List<DiaryImageDto> diaryImageDtos, Diary diary) {
        //
        return diaryImageDtos.stream().map(diaryImageDto -> {
            DiaryImage diaryImage = new DiaryImage();
            diaryImage.addDiary(diary);
            diaryImage.setImage(diaryImageDto.getDiaryImgUrl());
            return diaryImage;
        }).collect(Collectors.toList());
    }

    default DiariesDto.DiaryResponseDto diaryToDiaryResponseDto(CommentService commentService,
                                                                HeartService heartService,
                                                                CommentMapper commentMapper,
                                                                MemberMapper memberMapper,
                                                                DiaryImageService diaryImageService,
                                                                Diary diary) {

        DiariesDto.DiaryResponseDto diaryResponseDto = new DiariesDto.DiaryResponseDto();

        // Diary -> DiaryResponseDto
        diaryResponseDto.setDiaryId(diary.getDiaryId());
        diaryResponseDto.setDiaryStatus(diary.getDiaryStatus());
        diaryResponseDto.setContent(diary.getContent());

        // diary 작성자 추가
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponse(diary.getMember());
        diaryResponseDto.setMember(memberResponseDto);

        // diaryImage 추가. 단, 이미지가 없으면 본문(body)만 response로 전달되어야 함.
        diaryResponseDto.setDiaryImgUrl(diaryImagesToDiaryImageResponseDtos(
                diaryImageService.findVerifiedDiaryImages(diary)));

        // 댓글 추가
//        List<CommentResponseDto> commentResponseDtos
//                = commentMapper.commentToExistCommentResponseDtos(commentService, memberMapper, diary.getComments());
//        diaryResponseDto.setComments(commentResponseDtos);

        // 좋아요
        List<Heart> hearts = heartService.findExistHeartsByDiary(diary);
        List<Long> heartsUserId = hearts.stream()
                .map(heart -> heart.getMember().getMemberId())
                .collect(Collectors.toList());
        diaryResponseDto.setHeartMemberId(heartsUserId);

        System.out.println(diaryResponseDto);
        return diaryResponseDto;
    }

    default List<DiariesDto.DiaryResponseDto> diaryTodiaryesponseDtos(CommentService commentService,
                                                                      HeartService heartService,
                                                                      CommentMapper commentMapper,
                                                                      MemberMapper memberMapper,
                                                                      DiaryImageService diaryImageService,
                                                                      List<Diary> diary){
        return diary.stream()
                .map(diaries-> diaryToDiaryResponseDto(
                        commentService, heartService, commentMapper, memberMapper,
                        diaryImageService, diaries))
                .collect(Collectors.toList());
    }

    default List<DiaryImageResponseDto> diaryImagesToDiaryImageResponseDtos(List<DiaryImage> diaryImages) {
        return diaryImages
                .stream()
                .map(diaryImage -> {
                    DiaryImageResponseDto diaryImageResponseDto = new DiaryImageResponseDto();
                    diaryImageResponseDto.setDiaryImageId(diaryImage.getDiaryImageId());
                    diaryImageResponseDto.setDiaryImgUrl(diaryImage.getImage());
                    diaryImageResponseDto.setDiaryImageStatus(diaryImage.getDiaryImageStatus());
                    return diaryImageResponseDto;
                }).collect(Collectors.toList());
    }

//    default DiaryAndCommentResponseDto diaryToDiaryAndCommentResponseDto(
//            CommentService commentService, HeartService heartService, CommentMapper commentMapper,
//            MemberMapper memberMapper, DiaryImageService diaryImageService,
//            Diary diary, Integer commentPage, Integer commentSize, String commentSort) {
//
//        DiaryAndCommentResponseDto diaryAndCommentResponseDto = new DiaryAndCommentResponseDto();
//        diaryAndCommentResponseDto.setDiaryId(diary.getDiaryId());
//        diaryAndCommentResponseDto.setCreatedAt(diary.getCreatedAt());
//        diaryAndCommentResponseDto.setUpdatedAt(diary.getUpdatedAt());
//        diaryAndCommentResponseDto.setDiaryStatus(diary.getDiaryStatus());
//        diaryAndCommentResponseDto.setBody(diary.getBody());
//
//        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponse(diary.getMember());
//        diaryAndCommentResponseDto.setMember(memberResponseDto);
//
//        diaryAndCommentResponseDto.setDiaryImages(diaryImagesToDiaryImageResponseDtos( // diary(게시글)에 대한 이미지 속성 추가
//                diaryImageService.findVerifiedDiaryImages(diary) // 해당 게시글 속 이미지 중, status가 THREAD_IMAGE_EXIST만 반환
//        ));
//
//        Page<Comment> pageComments = commentService.findExistCommentsToPaginationAndSort(
//                diary, commentPage, commentSize, commentSort); //diary의 reply중 status가 true인 것만 페이지네이션 정렬해서 반환
//        List<Comment> comments = pageComments.getContent();
//        System.out.println(pageComments.getContent());
//        diaryAndCommentResponseDto.setComments(new MultiResponseDto<>(
//                commentMapper.commentsToCommentResponseDtos(memberMapper, comments), pageComments
//        ));
//
//        List<Heart> hearts = heartService.findExistHeartsByDiary(diary); // 해당 diary의 likes중에 Status가 LIKES_EXIST인 것만 반환
//        List<Long> HeartUserId = hearts.
//                stream().map(like -> like.getMember().getMemberId()).collect(Collectors.toList());
//        diaryAndCommentResponseDto.setHeartMemberId(hearMemberId);
//
//        return diaryAndCommentResponseDto;
//    }

}
