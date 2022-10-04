package it.mainPr.controller;

import it.mainPr.dto.followDto.FollowResponseDto;
import it.mainPr.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FollowController {
    private final FollowService followService;

    @PostMapping("/v1/follow")
    public ResponseEntity<Void> follow(@RequestParam(name = "member-id") long memberId) {
        followService.follow(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/v1/follow")
    public ResponseEntity<Void> unFollow(@RequestParam(name = "member-id") long memberId){
        followService.unFollow(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/v1/follow/follower")
    public ResponseEntity<List<FollowResponseDto>> getFollowers(@RequestParam(name = "member-id") long memberId) {
        return new ResponseEntity<>(followService.getFollowers(memberId), HttpStatus.OK);
    }

    @GetMapping("/v1/follow/following")
    public ResponseEntity<List<FollowResponseDto>> getFollowings(@RequestParam(name = "member-id") long memberId) {
        return new ResponseEntity<>(followService.getFollowings(memberId), HttpStatus.OK);
    }
}
