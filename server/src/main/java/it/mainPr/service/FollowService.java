package it.mainPr.service;

import it.mainPr.auth.utils.SecurityUtils;
import it.mainPr.dto.followDto.FollowResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.model.Follow;
import it.mainPr.model.Member;
import it.mainPr.repository.FollowRepository;
import it.mainPr.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    @Transactional
    public void follow(Long memberId) {
        String currentEmail = SecurityUtils.getCurrentMemberEmail();
        Member currentMember = memberRepository.findByEmail(currentEmail).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));

        Member followee = memberRepository.findByMemberId(memberId).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));

        checkValidation(currentMember, followee);
        Follow follow = new Follow(currentMember, followee);
        followRepository.save(follow);
    }

    @Transactional
    public void unFollow(Long memberId) {
        String currentEmail = SecurityUtils.getCurrentMemberEmail();
        Member currentMember = memberRepository.findByEmail(currentEmail).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));

        Member followee = memberRepository.findByMemberId(memberId).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));

        Follow follow = followRepository.findByFollowerAndFollowee(currentMember, followee).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.NOT_FOLLOWED));
        followRepository.delete(follow);
    }

    @Transactional
    public List<FollowResponseDto> getFollowings(Long memberId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));

        List<Follow> followings = followRepository.findByFollower(member);
        List<FollowResponseDto> following = new ArrayList<>(followings.size());
        for(Follow follow : followings) {
            following.add(FollowResponseDto.by(follow));
        }

        return following;
    }

    @Transactional
    public List<FollowResponseDto> getFollowers(Long memberId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() ->
                new BusinessLogicalException(ExceptionCode.MEMBER_NOT_FOUND));

        List<Follow> followers = followRepository.findByFollowee(member);
        List<FollowResponseDto> follower = new ArrayList<>(followers.size());
        for(Follow follow : followers) {
            follower.add(FollowResponseDto.of(follow));
        }

        return follower;
    }

    private void checkValidation(Member member, Member followee) {
        if(member == followee) {
            throw new BusinessLogicalException(ExceptionCode.CAN_NOT_FOLLOW);
        }
        followRepository.findByFollowerAndFollowee(member, followee)
                .ifPresent(e -> {
                    throw new IllegalStateException("이미 팔로우한 유저");
                });
    }
}


