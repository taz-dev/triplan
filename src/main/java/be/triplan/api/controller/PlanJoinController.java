package be.triplan.api.controller;

import be.triplan.api.entity.PlanJoin;
import be.triplan.api.service.PlanJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlanJoinController {

    private final PlanJoinService planJoinService;

    @GetMapping("/planJoins/{memberId}")
    public PlanJoin findPlanJoin(@PathVariable Long memberId) { //반환값 바꿔주기
        return planJoinService.findMemberId(memberId);
    }

}
