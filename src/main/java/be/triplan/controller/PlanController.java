package be.triplan.controller;

import be.triplan.dto.common.CommonResult;
import be.triplan.dto.common.ListResult;
import be.triplan.dto.common.SingleResult;
import be.triplan.dto.plan.PlanRequestDto;
import be.triplan.dto.plan.PlanResponseDto;
import be.triplan.service.PlanService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final ResponseService responseService;

    //계획 저장
    @PostMapping("/plans")
    public SingleResult<Long> savePlan(
            @RequestParam String planTitle,
            @RequestParam LocalDateTime startDateTime,
            @RequestParam LocalDateTime endDateTime) {

        PlanRequestDto planRequestDto = PlanRequestDto.builder()
                .planTitle(planTitle)
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .build();

        return responseService.getSingleResult(planService.save(planRequestDto));
    }

    //계획 전체 조회
    @GetMapping("/plans")
    public ListResult<PlanResponseDto> findAllPlans() {
        return responseService.getListResult(planService.findAllPlans());
    }

    //계획 단건 조회
    @GetMapping("/plans/{id}")
    public SingleResult<PlanResponseDto> findPlanById(@PathVariable Long id) {
        return responseService.getSingleResult(planService.findOne(id));
    }

    //계획 수정
    @PutMapping("/plans")
    public SingleResult<Long> updatePlan(
            @RequestParam Long id,
            @RequestParam String planTitle,
            @RequestParam LocalDateTime startDateTime,
            @RequestParam LocalDateTime endDateTime) {

        PlanRequestDto planRequestDto = PlanRequestDto.builder()
                .planTitle(planTitle)
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .build();
        return responseService.getSingleResult(planService.update(id, planRequestDto));
    }

    //계획 삭제
    @DeleteMapping("/plans/{id}")
    public CommonResult deletePlan(@PathVariable Long id) {
        planService.delete(id);
        return responseService.getSuccessResult();
    }

}
