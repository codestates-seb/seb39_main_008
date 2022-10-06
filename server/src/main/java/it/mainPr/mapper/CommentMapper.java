package it.mainPr.mapper;

import it.mainPr.dto.commentDto.CommentPatchDto;
import it.mainPr.dto.commentDto.CommentPostDto;
import it.mainPr.dto.commentDto.CommentResponseDto;
import it.mainPr.dto.memberDto.MemberResponseDto;
import it.mainPr.exception.BusinessLogicalException;
import it.mainPr.exception.ExceptionCode;
import it.mainPr.model.Comment;
import it.mainPr.model.Diary;
import it.mainPr.model.Member;
import it.mainPr.service.CommentService;
import it.mainPr.service.DiaryService;
import it.mainPr.service.MemberService;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    default Comment commentPostDtoComment(DiaryService diaryService, MemberService memberService, long diaryId, CommentPostDto commentPostDto) {
        Comment comment = new Comment();

        Diary diary = diaryService.findVerifiedDiary(diaryId);
        Member member = memberService.getLoginMember();

        comment.setContent(commentPostDto.getContent());
        comment.setDiary(diary);
        comment.setMember(member);

        return comment;
    }


    default Comment commentPostDtoToComment(DiaryService diaryService, MemberService memberService, long diaryId, CommentPostDto commentPostDto) {
        Comment comment = new Comment();

        Diary diary = diaryService.findVerifiedDiary(diaryId); // 존재하지 않는 diary라면 예외 처리
        Member member = memberService.getLoginMember(); // 로그인한 유저인지 확인.

        comment.setContent(commentPostDto.getContent());
        comment.setDiary(diary);
        comment.setMember(member);

        return comment;
    }

    default CommentResponseDto commentToCommentResponseDto(MemberMapper memberMapper, Comment comment) {
        CommentResponseDto commentResponseDto = new CommentResponseDto();
        commentResponseDto.setCommentId(comment.getCommentId());
        commentResponseDto.setCommentStatus(comment.getCommentStatus());
        commentResponseDto.setContent(comment.getContent());
        commentResponseDto.setDiaryId(comment.getDiary().getDiaryId());
        commentResponseDto.setCreatedAt(comment.getCreatedAt());
        commentResponseDto.setModifiedAt(comment.getModifiedAt());

        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponseDto(comment.getMember());
        commentResponseDto.setMember(memberResponseDto);

        return commentResponseDto;
    }

    default Comment commentPatchDtoToComment(
            CommentService commentService, MemberService memberService, long commentId, CommentPatchDto commentPatchDto) {

        // 로그인 유저 = comment 작성유저가 아니라면, 예외 처리. (수정 권한이 없기 때문에)
        if(memberService.getLoginMember() != commentService.findCommentMember(commentId)) {
            throw new BusinessLogicalException(ExceptionCode.ACCESS_DENIED_MEMBER);
        }

        Comment comment = new Comment();
        comment.setCommentId(commentId);
        comment.setContent(commentPatchDto.getContent());
        comment.setCommentStatus(commentPatchDto.getCommentStatus());

        return comment;
    }

    default List<CommentResponseDto> commentsToCommentResponseDtos(MemberMapper memberMapper, List<Comment> replies) {
        // 모든 댓글만 가지고 CommentResponseDtos반환
        return replies.stream()
                .filter(comment -> comment != null)
                .map(comment -> commentToCommentResponseDto(memberMapper, comment))
                .collect(Collectors.toList());
    }

    default List<CommentResponseDto> commentsToExistCommentResponseDtos(CommentService commentService,
                                                                   MemberMapper memberMapper,
                                                                   List<Comment> comments) {
        // 존재하는 댓글(REPLY_EXIST 상태)만 CommentResponseDtos로 반환
        if(comments == null) {
            System.out.println("댓글 상태가 REPLY_EXIST 또는 REPLY_NOT_EXIST 인지 상관 없이 DB에 존재하지 않습니다");
            return new ArrayList<>();
        } else {
            comments = commentService.findExistComments(comments); // comment 중 status가 REPLY_EXIST인 것만 반환
            return comments.stream()
                    .filter(comment -> comment != null)
                    .map(comment -> commentToCommentResponseDto(memberMapper, comment))
                    .collect(Collectors.toList());
        }
    }
}

