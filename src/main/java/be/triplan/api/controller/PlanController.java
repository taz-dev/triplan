package be.triplan.api.controller;

import be.triplan.api.entity.Plan;
import be.triplan.api.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlanController {

    //private final PlanService planService;
    private final PlanRepository planRepository;

    @GetMapping("/plans/{id}")
    public String findPlan(@PathVariable Long id) {
        Plan plan = planRepository.findById(id).get();
        return plan.getPlanTitle();
    }

}
