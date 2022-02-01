package be.triplan.service;

import be.triplan.entity.Member;
import be.triplan.entity.Plan;
import be.triplan.entity.PlanJoin;
import be.triplan.repository.MemberRepository;
import be.triplan.repository.PlanJoinRepository;
import be.triplan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanJoinService {

    private final PlanJoinRepository planJoinRepository;
    private final MemberRepository memberRepository;
    private final PlanRepository planRepository;

    public Long save(Long member_id, Long plan_id) {
        Member member = memberRepository.getById(member_id);
        Plan plan = planRepository.getById(plan_id);
        PlanJoin planJoin = PlanJoin.builder()
                .member(member)
                .plan(plan)
                .build();

        return planJoinRepository.save(planJoin).getId();
    }

/*    public Long findPlanByMemberId() {

    }*/
}
