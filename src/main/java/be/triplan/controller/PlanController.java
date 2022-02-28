package be.triplan.controller;

import be.triplan.dto.common.CommonResult;
import be.triplan.dto.common.ListResult;
import be.triplan.dto.common.SingleResult;
import be.triplan.dto.plan.PlanDto;
import be.triplan.dto.plan.PlanInsertRequestDto;
import be.triplan.dto.plan.PlanUpdateRequestDto;
import be.triplan.entity.Map;
import be.triplan.entity.MapStatus;
import be.triplan.entity.Member;
import be.triplan.repository.MapRepository;
import be.triplan.service.PlanJoinService;
import be.triplan.service.PlanService;
import be.triplan.service.common.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final PlanJoinService planJoinService;
    private final ResponseService responseService;
    private final MapRepository mapRepository;

    /**
     * 계획 저장
     * 1. 여행명, 여행기간, 이미지 저장
     * 2. 출발위치(MapStatus.START), 도착위치(MapStatus.END)는 MAP 테이블에 따로 저장
     * 3. 이미지는 도착위치 이미지로 저장
     */
    @PostMapping("/plans")
    public SingleResult<Long> savePlan(@AuthenticationPrincipal Member member, @RequestBody PlanInsertRequestDto requestDto) {

        //1
        PlanDto planDto = PlanDto.builder()
                .planTitle(requestDto.getPlanTitle())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .planImage(requestDto.getMap().get(1).getPlanImage()) //3
                .build();
        //2
        for (int i = 0; i < requestDto.getMap().size(); i++) {

            if (i == 0) {
                Map map = Map.builder()
                        .mapImage(requestDto.getMap().get(i).getPlanImage())
                        .locationX(requestDto.getMap().get(i).getLocationX())
                        .locationY(requestDto.getMap().get(i).getLocationY())
                        .address(requestDto.getMap().get(i).getAddress())
                        .addressDetail(requestDto.getMap().get(i).getAddressDetail())
                        .mapStatus(MapStatus.START)
                        .build();

                mapRepository.save(map);

            } else {
                Map map = Map.builder()
                        .mapImage(requestDto.getMap().get(i).getPlanImage())
                        .locationX(requestDto.getMap().get(i).getLocationX())
                        .locationY(requestDto.getMap().get(i).getLocationY())
                        .address(requestDto.getMap().get(i).getAddress())
                        .addressDetail(requestDto.getMap().get(i).getAddressDetail())
                        .mapStatus(MapStatus.END)
                        .build();

                mapRepository.save(map);
            }
        }
        return responseService.getSingleResult(planService.save(member.getId(), planDto));
    }

    /**
     * 나의 계획 전체목록 조회
     */
    @GetMapping("/plans/my")
    public ListResult<PlanDto> findMyPlans(@AuthenticationPrincipal Member member) {
        List<PlanDto> myPlan = planJoinService.findMyPlan(member.getId());
        return responseService.getListResult(myPlan);
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
    public SingleResult<Long> updatePlan(@RequestBody PlanUpdateRequestDto requestDto) {

        PlanDto planDto = PlanDto.builder()
                .planTitle(requestDto.getPlanTitle())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .build();

        return responseService.getSingleResult(planService.update(requestDto.getPlanId(), planDto));
    }

    /**
     * 계획 삭제
     */
    @DeleteMapping("/plans/{id}")
    public CommonResult deletePlan(@AuthenticationPrincipal Member member, @PathVariable Long id) {
        planService.delete(member, id);
        return responseService.getSuccessResult();
    }
}
