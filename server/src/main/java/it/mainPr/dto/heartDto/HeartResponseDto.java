package it.mainPr.dto.heartDto;

import it.mainPr.dto.diaryDto.DiariesDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.model.Heart;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class HeartResponseDto {
     private long heartId;
     private LocalDateTime createdAt;
     private LocalDateTime modifiedAt;
     private Heart.HeartStatus heartStatus;
     private MemberResponseDto member;
     private DiariesDto.DiaryResponseDto diary;

}
