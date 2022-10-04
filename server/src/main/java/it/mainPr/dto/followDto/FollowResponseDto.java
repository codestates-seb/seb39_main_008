package it.mainPr.dto.followDto;

import it.mainPr.model.Follow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class FollowResponseDto {
    private String email;
    private String name;
    private String nickname;

    public static FollowResponseDto of(Follow follow) {
        return new FollowResponseDto(follow.getFollower().getEmail(), follow.getFollower().getName(), follow.getFollower().getNickname());
    }

    public static FollowResponseDto by(Follow follow) {
        return new FollowResponseDto(follow.getFollowee().getEmail(), follow.getFollowee().getName(), follow.getFollowee().getNickname());
    }
}
