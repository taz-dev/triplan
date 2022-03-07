package be.triplan.controller;

import be.triplan.dto.checklist.ChecklistDto;
import be.triplan.dto.checklist.ChecklistInsertRequestDto;
import be.triplan.dto.checklist.ChecklistUpdateRequestDto;
import be.triplan.dto.common.CommonResult;
import be.triplan.dto.common.ListResult;
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
    public SingleResult<Long> saveChecklist(@RequestBody ChecklistInsertRequestDto requestDto) {

        Long plan_id = requestDto.getPlanId();

        ChecklistDto checklistDto = ChecklistDto.builder()
                .checkItem(requestDto.getCheckItem())
                .isSelected(requestDto.getIsSelected())
                .build();

        return responseService.getSingleResult(checklistService.save(plan_id, checklistDto));
    }

    /**
     * 체크리스트 전체 조회
     */
    @GetMapping("/checklists")
    public ListResult<ChecklistDto> findAllChecklists() {
        return responseService.getListResult(checklistService.findAllChecklists());
    }

    /**
     * 체크리스트 수정
     */
    @PutMapping("/checklists")
    public SingleResult<Long> updateCheckList(@RequestBody ChecklistUpdateRequestDto requestDto) {

        Long checklist_id = requestDto.getChecklistId();

        ChecklistDto checklistDto = ChecklistDto.builder()
                .checkItem(requestDto.getCheckItem())
                .isSelected(requestDto.getIsSelected())
                .build();

        return responseService.getSingleResult(checklistService.update(checklist_id, checklistDto));
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
