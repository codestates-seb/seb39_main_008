package it.mainPr.service;

import it.mainPr.model.Comment;
import it.mainPr.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final MemberService memberService;
    private final DiaryService diaryService;
    private final CommentRepository commentRepository;

//    @Value("${app.pageable.size.comment}")
    private int size;

//    public Page<Comment>

}
