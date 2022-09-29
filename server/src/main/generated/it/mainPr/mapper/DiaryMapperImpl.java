package it.mainPr.mapper;

import it.mainPr.dto.DiariesDto.DiaryResponseDto;
import it.mainPr.dto.DiariesDto.DiaryResponseDto.DiaryResponseDtoBuilder;
import it.mainPr.dto.DiariesDto.PostDto;
import it.mainPr.model.Diary;
import it.mainPr.model.Diary.DiaryBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-30T00:18:51+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class DiaryMapperImpl implements DiaryMapper {

    @Override
    public Diary postDtoToDiary(PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        DiaryBuilder diary = Diary.builder();

        diary.title( postDto.getTitle() );
        diary.content( postDto.getContent() );
        diary.diaryImgUrl( postDto.getDiaryImgUrl() );

        return diary.build();
    }

    @Override
    public DiaryResponseDto diaryToResponseDto(Diary diary) {
        if ( diary == null ) {
            return null;
        }

        DiaryResponseDtoBuilder diaryResponseDto = DiaryResponseDto.builder();

        diaryResponseDto.diaryId( diary.getDiaryId() );
        diaryResponseDto.title( diary.getTitle() );
        diaryResponseDto.nickname( diary.getNickname() );
        diaryResponseDto.diaryImgUrl( diary.getDiaryImgUrl() );
        diaryResponseDto.content( diary.getContent() );
        diaryResponseDto.member( diary.getMember() );

        return diaryResponseDto.build();
    }

    @Override
    public List<DiaryResponseDto> diaryListToResponseDtoList(List<Diary> diaries) {
        if ( diaries == null ) {
            return null;
        }

        List<DiaryResponseDto> list = new ArrayList<DiaryResponseDto>( diaries.size() );
        for ( Diary diary : diaries ) {
            list.add( diaryToResponseDto( diary ) );
        }

        return list;
    }
}
