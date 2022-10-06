package it.mainPr.mapper;

import it.mainPr.dto.commentDto.CommentResponseDto;
import it.mainPr.dto.diaryDto.DiariesDto;
import it.mainPr.dto.diaryDto.DiaryImageDto;
import it.mainPr.dto.diaryDto.DiaryImageResponseDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.model.*;
import it.mainPr.service.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DiaryMapper {

//    Diary postDtoToDiary(DiariesDto.PostDto postDto);
//    DiariesDto.DiaryResponseDto diaryToResponseDto(Diary diary);


    default Diary diaryPostDtoToDiary(BookService bookService, MemberService memberService, long bookId, DiariesDto.PostDto postDto) {
        Diary diary = new Diary();

        Book book = bookService.findVerifiedBook(bookId);
        Member member = memberService.getLoginMember();

        System.out.println("로그인 유저:" + member.getMemberId());
        System.out.println("북 소유자:" + book.getMember().getMemberId());

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

        return diaryImageDtos.stream().map(diaryImageDto -> {
            DiaryImage diaryImage = new DiaryImage();
            diaryImage.addDiary(diary);
            diaryImage.setImage(diaryImageDto.getDiaryImgUrl());
            return diaryImage;
        }).collect(Collectors.toList());
    }

    default DiariesDto.DiaryResponseDto diaryToDiaryResponseDto(
                                                                CommentService commentService,
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
        diaryResponseDto.setBookId(diary.getBook().getBookId());

        // diary 작성자 추가
        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(diary.getMember());
        diaryResponseDto.setMember(memberResponseDto);

        // diaryImage 추가. 단, 이미지가 없으면 본문(body)만 response로 전달되어야 함.
        diaryResponseDto.setDiaryImgUrl(diaryImagesToDiaryImageResponseDtos(
                diaryImageService.findVerifiedDiaryImages(diary)));

        // 댓글 추가
        List<CommentResponseDto> commentResponseDtos
                = commentMapper.commentsToExistCommentResponseDtos(commentService, memberMapper, diary.getComments());
        diaryResponseDto.setComments(commentResponseDtos);

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

    default List<DiariesDto.DiaryResponseDto> diariesToDiaryResponse(MemberMapper memberMapper, List<Diary> diaries) {
        //모든리뷰만 가지고 DiaryResponseDtos반환
        return diaries.stream().filter(diary -> diary != null).map(diary -> diaryToDiaryResponse(memberMapper, diary)).
                collect(Collectors.toList());
    }

    default List<DiariesDto.DiaryResponseDto> diariesToExistDiaryResponseDtos(DiaryService diaryService, MemberMapper memberMapper,List<Diary> diaries){
        //리뷰중에 존재하는 리뷰(REVIEW_EXIST 상태)만 가지고 DiaryResponseDtos반환

        if(diaries == null){
            System.out.println("일기가 status 상태가 DIARY_EXIST/DIARY_NOT_EXIST인지 상관없이 DB에 존재하지 않습니다");
            return new ArrayList<>();
        }else{
            diaries = diaryService.findExistDiaries(diaries); // diarys인자중 status가 REVIEW_EXIST인 것만 반환
            return diaries.stream().filter(diary -> diary != null).map(diary -> diaryToDiaryResponse(memberMapper, diary)).collect(Collectors.toList());
        }
    }


    default DiariesDto.DiaryResponseDto diaryToDiaryResponse(MemberMapper memberMapper, Diary diary) {

        DiariesDto.DiaryResponseDto diaryResponse = new DiariesDto.DiaryResponseDto();
        diaryResponse.setDiaryId(diary.getDiaryId());
        diaryResponse.setDiary_title(diary.getDiary_title());
        diaryResponse.setDiary_subtitle(diary.getDiary_subtitle());
        diaryResponse.setContent(diary.getContent());
        diaryResponse.setDiaryStatus(diary.getDiaryStatus());
        diaryResponse.setBookId(diary.getBook().getBookId());

        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(diary.getMember());
        diaryResponse.setMember(memberResponseDto);


        return diaryResponse;
    }
}
