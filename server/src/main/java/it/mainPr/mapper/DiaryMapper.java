package it.mainPr.mapper;

import it.mainPr.dto.DiariesDto;
import it.mainPr.model.Diary;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiaryMapper {
    Diary postDtoToDiary(DiariesDto.PostDto postDto);
    DiariesDto.DiaryResponseDto diaryToResponseDto(Diary diary);
    List<DiariesDto.DiaryResponseDto> diaryListToResponseDtoList(List<Diary> diaries);
}