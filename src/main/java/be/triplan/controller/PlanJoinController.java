package be.triplan.controller;

import be.triplan.service.MemberService;
import be.triplan.service.PlanJoinService;
import be.triplan.service.PlanService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlanJoinController {

    private final PlanJoinService planJoinService;
    private final MemberService memberService;
    private final PlanService planService;
    private final ResponseService responseService;

    /**
     * 유저의 전체 계획 목록 조회
     */
/*    @GetMapping("/joins/myplan")
    public ListResult<PlanJoinInterface> findMyPlan() {
        return responseService.getListResult(planJoinService.findMyPlan());
    }*/

}
