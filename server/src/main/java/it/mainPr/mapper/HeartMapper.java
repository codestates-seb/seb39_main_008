package it.mainPr.mapper;

import it.mainPr.dto.heartDto.HeartResponseDto;
import it.mainPr.model.Heart;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HeartMapper {
    List<HeartResponseDto> heartToHeartResponseDtos(List<Heart> hearts);

    default public HeartResponseDto heartToHeartResponseDto(Heart heart) {
        HeartResponseDto heartResponseDto = new HeartResponseDto();
        heartResponseDto.setMemberId(heart.getMember().getMemberId());
        heartResponseDto.setDiaryId(heart.getDiary().getDiaryId());
        heartResponseDto.setDiaryTitle(heart.getDiary().getDiary_title());
        heartResponseDto.setDiaryContent(heart.getDiary().getContent());

        return heartResponseDto;

    }
}
