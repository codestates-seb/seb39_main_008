package it.mainPr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class MemberPostDto {

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 옳은지 확인해주세요")
    private String email;
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

//    @NotBlank(message = "이름을 입력해주세요")
    private String name;
//    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;

    @Builder
    public MemberPostDto(String email, String password, String name, String nickname){
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
    }
}

