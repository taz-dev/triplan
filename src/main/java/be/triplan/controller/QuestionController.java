package be.triplan.controller;

import be.triplan.dto.common.SingleResult;
import be.triplan.dto.question.QuestionInsertRequestDto;
import be.triplan.dto.question.QuestionDto;
import be.triplan.service.QuestionService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final ResponseService responseService;
    private final QuestionService questionService;

    /**
     * 문의 저장
     */
    @PostMapping("/questions")
    public SingleResult<Long> saveQuestion(@RequestBody QuestionInsertRequestDto requestDto) {

        Long member_id = requestDto.getMemberId();

        QuestionDto questionDto = QuestionDto.builder()
                .questionTitle(requestDto.getQuestionTitle())
                .questionContent(requestDto.getQuestionContent())
                .questionImage(requestDto.getQuestionImage())
                .build();

        return responseService.getSingleResult(questionService.save(member_id, questionDto));
    }
}
