package be.triplan.controller;

import be.triplan.dto.common.SingleResult;
import be.triplan.dto.question.QuestionRequestDto;
import be.triplan.service.QuestionService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final ResponseService responseService;
    private final QuestionService questionService;

    @PostMapping("/questions/")
    public SingleResult<Long> saveQuestion(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String image) {

        QuestionRequestDto questionRequestDto = QuestionRequestDto.builder()
                .title(title)
                .content(content)
                .image(image)
                .build();

        return responseService.getSingleResult(questionService.save(questionRequestDto));
    }
}
