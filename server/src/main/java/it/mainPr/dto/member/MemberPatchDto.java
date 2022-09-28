package it.mainPr.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberPatchDto {

    private String information;
    private String imgUrl;

    @Builder
    public MemberPatchDto(String information, String imgUrl) {
        this.information = information;
        this.imgUrl = imgUrl;
    }
}
