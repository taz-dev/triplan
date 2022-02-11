package be.triplan.controller;

import be.triplan.dto.common.CommonResult;
import be.triplan.dto.common.ListResult;
import be.triplan.dto.common.SingleResult;
import be.triplan.dto.plan.PlanDto;
import be.triplan.dto.plan.PlanInsertRequestDto;
import be.triplan.dto.plan.PlanUpdateRequestDto;
import be.triplan.entity.Member;
import be.triplan.service.MemberService;
import be.triplan.service.PlanService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final MemberService memberService;
    private final ResponseService responseService;

    /**
     * 계획 저장
     */
    @PostMapping("/plans")
    public SingleResult<Long> savePlan(@RequestBody PlanInsertRequestDto requestDto) {

/*        String email = principal.getName();
        Member member = memberService.findMemberByEmail(email);
        Long member_id = member.getId();*/

        Member member = memberService.findMemberByEmail(requestDto.getEmail());

        PlanDto planDto = PlanDto.builder()
                .planTitle(requestDto.getPlanTitle())
                .startDateTime(requestDto.getStartDate())
                .endDateTime(requestDto.getEndDate())
                .planImage(requestDto.getPlanImage())
                .locationX(requestDto.getLocationX())
                .locationY(requestDto.getLocationY())
                .address(requestDto.getAddress())
                .addressDetail(requestDto.getAddressDetail())
                .build();

        return responseService.getSingleResult(planService.save(member.getId(), planDto));
    }

    /**
     * 계획 전체목록 조회
     */
    @GetMapping("/plans")
    public ListResult<PlanDto> findAllPlans() {
        return responseService.getListResult(planService.findAllPlans());
    }

    /**
     * 계획 단건 조회
     */
    @GetMapping("/plans/{id}")
    public SingleResult<PlanDto> findPlanById(@PathVariable Long id) {
        return responseService.getSingleResult(planService.findOne(id));
    }

    /**
     * 계획 수정
     */
    @PutMapping("/plans")
    public SingleResult<Long> updatePlan(
            @RequestParam Long id,
            @RequestParam String planTitle,
            @RequestParam LocalDateTime startDateTime,
            @RequestParam LocalDateTime endDateTime) {

        PlanUpdateRequestDto planUpdateRequestDto = PlanUpdateRequestDto.builder()
                .planTitle(planTitle)
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .build();

        return responseService.getSingleResult(planService.update(id, planUpdateRequestDto));
    }

    /**
     * 계획 삭제
     */
    @DeleteMapping("/plans/{id}")
    public CommonResult deletePlan(@PathVariable Long id) {
        planService.delete(id);
        return responseService.getSuccessResult();
    }
}
