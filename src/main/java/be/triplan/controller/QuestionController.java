package be.triplan.controller;

import be.triplan.dto.common.SingleResult;
import be.triplan.dto.question.QuestionDto;
import be.triplan.dto.question.QuestionInsertRequestDto;
import be.triplan.entity.Member;
import be.triplan.service.QuestionService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final ResponseService responseService;
    private final QuestionService questionService;

    /**
     * 문의 저장(일단 DB에 저장 -> 나중에 관리자 이메일로)
     */
    @PostMapping("/questions")
    public SingleResult<Long> saveQuestion(@AuthenticationPrincipal Member member,
                                           @RequestBody QuestionInsertRequestDto requestDto) {

        QuestionDto questionDto = QuestionDto.builder()
                .questionTitle(requestDto.getComplainName())
                .questionContent(requestDto.getComplain())
                .questionImage(requestDto.getSource())
                .build();

        return responseService.getSingleResult(questionService.save(member.getId(), questionDto));
    }
}
