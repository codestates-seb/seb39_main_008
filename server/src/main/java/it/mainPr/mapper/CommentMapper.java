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

    default CommentResponseDto commentToCommentResponseDto(MemberMapper memberMapper, Comment comment) {
        CommentResponseDto commentResponseDto = new CommentResponseDto();

        commentResponseDto.setCommentId(comment.getCommentId());
        System.out.println("댓글 ID :" + comment.getCommentId());
        commentResponseDto.setCommentStatus(comment.getCommentStatus());
        System.out.println("댓글 상태 :" + comment.getCommentStatus());
        commentResponseDto.setContent(comment.getContent());
        commentResponseDto.setDiaryId(comment.getDiary().getDiaryId());
        System.out.println("게시글 ID : " + comment.getDiary().getDiaryId());
        commentResponseDto.setCreatedAt(comment.getCreatedAt());
        commentResponseDto.setModifiedAt(comment.getModifiedAt());

        MemberResponseDto memberResponseDto = memberMapper.memberToMemberResponse(comment.getMember());
        commentResponseDto.setMember(memberResponseDto);

        return commentResponseDto;
    }

    default Comment commentPatchDtoToComment( CommentService commentService, MemberService memberService, long commentId, CommentPatchDto commentPatchDto) {

        if (!memberService.getLoginMember().getMemberId().equals(commentService.findCommentMember(commentId).getMemberId())) {
            throw new BusinessLogicalException(ExceptionCode.NO_AUTHORIZED);
        }

        Comment comment = new Comment();
        comment.setCommentId(commentId);
        comment.setContent(commentPatchDto.getContent());
        comment.setCommentStatus(commentPatchDto.getCommentStatus());

        return comment;
    }
}

