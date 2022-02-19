package be.triplan.service;

import be.triplan.dto.PlanJoinInterface;
import be.triplan.repository.MemberRepository;
import be.triplan.repository.PlanJoinRepository;
import be.triplan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanJoinService {

    private final PlanJoinRepository planJoinRepository;
    private final MemberRepository memberRepository;
    private final PlanRepository planRepository;

/*    public List<PlanJoinInterface> findMyPlan() {
        List<PlanJoinInterface> plans = planJoinRepository.findAllPlansByMember();
        return plans;
    }*/

/*    public List<PlanJoinDto> findAllPlanByMember() {
        return planJoinRepository.findAllPlansByMember()
                .stream()
                .map(PlanJoinDto::new)
                .collect(Collectors.toList());
    }*/

/*    public Long save(Long member_id, Long plan_id) {

        Member member = memberRepository.getById(member_id);
        Plan plan = planRepository.getById(plan_id);

        PlanJoin planJoin = PlanJoin.builder()
                .member(member)
                .plan(plan)
                .build();

        return planJoinRepository.save(planJoin).getId();
    }*/
}
