//package it.mainPr.service;
//
//import it.mainPr.dto.diary.DiaryResponseDto;
//import it.mainPr.repository.LikeRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class LikeService {
//
//    private final LikeRepository likeRepository;
//    private final DiaryService diaryService;
//
//    public boolean booleanLike(Long memberId, Long diaryId) {
//        DiaryResponseDto diaries = diaryService.findById(diaryId);
//
//        if(isNotAlreadyLike(memberId, diaryId)) {
//            diaries.setLikeCount(diaries.getLikeCount() + 1);
//        }
//    }
//}
