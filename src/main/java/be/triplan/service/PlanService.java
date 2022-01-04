package be.triplan.service;

import be.triplan.entity.Plan;
import be.triplan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {

    private final PlanRepository planRepository;

    //계획 생성
    @Transactional
    public Long create() {

        Plan plan = Plan.createPlan();

        planRepository.save(plan);

        return plan.getId();
    }

    //계획 삭제
    @Transactional
    public void delete(Long id) {
        planRepository.deleteById(id);
    }
}
