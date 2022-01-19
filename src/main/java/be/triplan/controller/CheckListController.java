package be.triplan.controller;

import be.triplan.dto.checklist.CheckListDto;
import be.triplan.dto.checklist.CheckListRequestDto;
import be.triplan.dto.common.SingleResult;
import be.triplan.dto.question.QuestionDto;
import be.triplan.dto.question.QuestionRequestDto;
import be.triplan.service.ChecklistService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CheckListController {

    private final ChecklistService checklistService;
    private final ResponseService responseService;

    /**
     * 체크리스트 저장
     */
    @PostMapping("/checklists")
    public SingleResult<Long> saveCheckList(@RequestBody CheckListRequestDto requestDto) {

        Long plan_id = requestDto.getPlanId();

        CheckListDto checkListDto = CheckListDto.builder()
                .checkItem(requestDto.getCheckItem())
                .isSelected(requestDto.getIsSelected())
                .build();

        return responseService.getSingleResult(checklistService.save(plan_id, checkListDto));
    }
}
