package be.triplan.controller;

import be.triplan.dto.common.CommonResult;
import be.triplan.entity.Plan;
import be.triplan.repository.PlanRepository;
import be.triplan.service.PlanService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final ResponseService responseService;
    private final PlanRepository planRepository;

    @GetMapping("/plans/{id}")
    public String findPlan(@PathVariable Long id) {
        Plan plan = planRepository.findById(id).get();
        return plan.getPlanTitle();
    }

    //계획 삭제
    @DeleteMapping("/plans/{id}")
    public CommonResult deletePlan(@PathVariable Long id) {
        planService.delete(id);
        return responseService.getSuccessResult();
    }

}
