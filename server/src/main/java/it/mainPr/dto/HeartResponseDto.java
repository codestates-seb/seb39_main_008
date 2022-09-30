package it.mainPr.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeartResponseDto {
     long diaryId;
     long memberId;
     String diaryTitle;
     String diaryContent;
     int heartCounts;


}
