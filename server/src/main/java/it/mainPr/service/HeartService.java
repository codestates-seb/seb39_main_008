package it.mainPr.service;

import it.mainPr.dto.DiariesDto;
import it.mainPr.dto.MemberResponseDto;
import it.mainPr.model.Heart;
import it.mainPr.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HeartService {
    private final MemberService memberService;
    private final DiaryService diaryService;
    private final HeartRepository heartRepository;

}
