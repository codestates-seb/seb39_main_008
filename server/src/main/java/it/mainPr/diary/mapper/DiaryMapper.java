package it.mainPr.diary.mapper;

import it.mainPr.diary.dto.DiaryDto;
import it.mainPr.diary.dto.DiaryResponseDto;
import it.mainPr.diary.entity.Diary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiaryMapper {
    Diary diaryPostToDiary(DiaryDto.Post requestBody);
    Diary diaryPatchToDiary(DiaryDto.Patch requestBody);
    DiaryResponseDto diaryToDiaryResponse(Diary diary);
}
