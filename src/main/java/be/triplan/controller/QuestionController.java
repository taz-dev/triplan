package be.triplan.controller;

import be.triplan.dto.common.SingleResult;
import be.triplan.dto.question.QuestionRequestDto;
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

    @PostMapping("/questions")
    public SingleResult<Long> saveQuestion(@RequestBody QuestionRequestDto requestDto) {

        Long member_id = requestDto.getMember_id();

        QuestionDto questionDto = QuestionDto.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .image(requestDto.getImage())
                .build();

        return responseService.getSingleResult(questionService.save(member_id, questionDto));
    }
}
