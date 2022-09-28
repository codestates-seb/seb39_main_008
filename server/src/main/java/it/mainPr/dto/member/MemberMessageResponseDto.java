package it.mainPr.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberMessageResponseDto {

    //상태 알림 메세지 : ex) 가입완료 / 아이디를 다시 입력해주세요 등
    private String message;
}
