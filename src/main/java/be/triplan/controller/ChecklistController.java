package be.triplan.controller;

import be.triplan.dto.checklist.ChecklistDto;
import be.triplan.dto.checklist.ChecklistRequestDto;
import be.triplan.dto.common.CommonResult;
import be.triplan.dto.common.SingleResult;
import be.triplan.service.ChecklistService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChecklistController {

    private final ChecklistService checklistService;
    private final ResponseService responseService;

    /**
     * 체크리스트 저장
     */
    @PostMapping("/checklists")
    public SingleResult<Long> saveChecklist(@RequestBody ChecklistRequestDto requestDto) {

        Long plan_id = requestDto.getPlanId();

        ChecklistDto checkListDto = ChecklistDto.builder()
                .checkItem(requestDto.getCheckItem())
                .isSelected(requestDto.getIsSelected())
                .build();

        return responseService.getSingleResult(checklistService.save(plan_id, checkListDto));
    }

    /**
     * 체크리스트 삭제
     */
    @DeleteMapping("/checklists/{id}")
    public CommonResult deleteChecklist(@PathVariable Long id) {
        checklistService.delete(id);
        return responseService.getSuccessResult();
    }
}
