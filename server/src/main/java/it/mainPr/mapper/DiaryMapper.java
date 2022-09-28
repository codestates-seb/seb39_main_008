package it.mainPr.mapper;

import it.mainPr.dto.DiariesDto;
import it.mainPr.model.Diary;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiaryMapper {

    default  DiariesDto.DiaryResponseDto diariesToDiariesViewDto(Diary diary) {

        DiariesDto.DiaryResponseDto responseDto = DiariesDto.DiaryResponseDto.builder()
                .diaryId(diary.getDiaryId())
                .title(diary.getTitle())
                .subtitle(diary.getSubTitle())
                .content(diary.getContent())
                .diaryImgUrl(diary.getDiaryImgUrl())
//                .likeCount(diary.getLikeCount)
//                .followCount(diary.getFollowCount)
                .build();

        return responseDto;
    }
}
